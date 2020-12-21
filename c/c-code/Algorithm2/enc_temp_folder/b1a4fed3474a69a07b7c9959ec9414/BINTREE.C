/*--- bintree.c --------------------------- Listing 6-1 ---------
 * Binary tree routines. Provides plain binary search
 *
 *-------------------------------------------------------------*/
#pragma warning(disable : 4996) 
#define TEST
#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include <ctype.h>
#include "bintree.h"
#include "stacks.h"



/* A safe malloc() */
static void * tmalloc(size_t size)
{
    void *p;
    if ((p = malloc(size)) == NULL) {
        printf("Out of memory\n");
        exit(1);
    }

    return p;
}

/*
 * Create and initialize a node for the user. 'size' both can
 * and should be greater than sizeof(Bnode) to allow for a
 * data area for the user.
 */
Bnode *InitBintreeNode(size_t size)
{
    Bnode *n;

    n = tmalloc(size);
    n -> link[LEFT] = n -> link[RIGHT]  = NULL;

    return n;
}

/* Create an empty tree */
Bintree *NewBintree (Bnode *dummy,
    CompFunc cf,
    int dup_ok,
    size_t node_size)
{
    Bintree *t;

    t = tmalloc(sizeof(Bintree));
    t -> DummyHead = dummy;
    t -> Compare = cf;
    t -> DuplicatesOk = dup_ok;
    t -> NodeSize = node_size;

    return t;
}


/* Find node n in tree t */
Bnode *FindBintree(Bintree *t, Bnode *n)
{
    Bnode *s;
    int dir;

    s = t -> DummyHead -> link[RIGHT];
    while (s != NULL) {
        dir = (t -> Compare) (n, s);
        /*
         * If a match, we're done.
         * For Red-Black, must also be a leaf.
         */
        if (dir == 0 )
            return s;
        dir = dir < 0;
        s = s -> link[dir];
    }
    return NULL; /* no match */

}

Bnode * DelBintree (Bintree *t, Bnode *n)
{

    Bnode *p, *s, *save;
    int dir, dir_old;

    p = t -> DummyHead;
    s = p -> link[RIGHT];
    dir_old = dir = RIGHT;

    /* Look for a match */
    while (s != NULL && (dir = (t->Compare)(n, s)) != 0) {
        p = s;
        dir = dir < 0;
        dir_old = dir;
        s = p -> link[dir];
    }

    if (s == NULL)
        return NULL; /* no match found */

    save = s;
    /*
     * First case: if s has no right child, then replace s
     * with s's left child.
     */
    if (s -> link[RIGHT] == NULL)
        s = s -> link[LEFT];
    /*
     * Second case: if s has a right child that lacks a left
     * child, then replace s with s's right child and
     * copy s's left child into the right child's left child.
     */
    else if (s -> link[RIGHT] -> link[LEFT] == NULL) {
        s = s -> link[RIGHT];
        s -> link[LEFT] = save -> link[LEFT];
    }
    /*
     * Final case: find leftmost (smallest) node in s's right
     * subtree. By definition, this node has an empty left
     * link. Free this node by copying its right link to
     * its parent's left link and then give it both of s's
     * links (thus replacing s).
     */
    else {
        Bnode *small;

        small = s -> link[RIGHT];
        while (small -> link[LEFT] -> link[LEFT])
            small = small -> link[LEFT];
        s = small -> link[LEFT];
        small -> link[LEFT] = s -> link[RIGHT];
        s -> link[LEFT] = save -> link[LEFT];
        s -> link[RIGHT] = save -> link[RIGHT];
    }

    p -> link[dir_old] = s;

    return save;
}

/* Insert node n into tree t */
int InsBintree (Bintree *t, Bnode *n)
{
    int p_dir;
    Bnode *p, *s;

    /* Search until we find an empty arm. */
    p = t -> DummyHead;
    p_dir = RIGHT; /* direction from p to s */
    s = p -> link[RIGHT];

    while (s != NULL) {
        p = s;
        p_dir = (t -> Compare) (n, s);
        if (p_dir == 0 && t -> DuplicatesOk == 0)
            return TREE_FAIL; /* duplicate */
        p_dir = p_dir < 0;
		//printf(" Compare with '%s'.", ((struct sMynode *)s) -> text);
        s = s -> link[p_dir];
    }

    /* Add the new node */
    p -> link[p_dir] = n;
	//printf("\n");
    return TREE_OK;

}

/*
 * Recursive tree walk routines. The entry point is
 * WalkBintree. It will do an inorder traversal of the
 * tree, call df() for each node and leaf.
 */
void rWalk(Bnode *n, int level, DoFunc df)
{
    if (n != NULL) {
        rWalk(n -> link[LEFT], level + 1, df);
        df(n, level);
        rWalk(n -> link[RIGHT], level + 1, df);
    }
}

int WalkBintree(Bintree *t, DoFunc df)
{
    if (t -> DummyHead -> link[RIGHT] == NULL) {
        fputs("Empty tree\n", stdout);
        return TREE_FAIL;
    }

    rWalk(t -> DummyHead -> link[RIGHT], 0, df);
    return TREE_OK;
}

#if defined(TEST)
/*
 * Test driver
 */

#define BUFLEN 100



int LoadString(Bintree *t, char *string)
{
    Mynode *m;

    m = (Mynode *) InitBintreeNode(sizeof(Mynode));
    strncpy(m->text, string, sizeof(m->text));
    m->text[sizeof(m->text) - 1] = 0;

    return InsBintree(t, (Bnode *) m);
}

void FindString(Bintree *t, char *string)
{
    Mynode m, *r;
    strncpy(m.text, string, sizeof(m.text));
    m.text[sizeof(m.text) - 1] = 0;
    if ((r = (Mynode *) FindBintree(t, (Bnode *) &m)) == NULL)
        puts(" Not found.\n");
    else
        printf(" Found '%s'.\n", r -> text);
}

void DeleteString(Bintree *t, char *string)
{
    Mynode m, *n;
    strncpy(m.text, string, sizeof(m.text));
    m.text[sizeof(m.text) - 1] = 0;
    n = (Mynode *) DelBintree(t, (Bnode *) &m);
    if (n)
        free (n);
    else
        fprintf(stdout, " Did not find '%s'.\n", string);
}

void LoadFile(Bintree *t, char *fname)
{
    FILE *infile;
    char buffer[BUFLEN], *s;
    int i = 0, j = 0;

    if ((infile = fopen(fname, "r")) == NULL) {
        fputs(" Couldn't open the file.\n", stdout);
        return;
    }

    while (fgets(buffer, BUFLEN, infile)) {
        s = buffer + strlen(buffer);
        while(iscntrl(*s))
            *s-- = 0;
        if (buffer[0] == ';') /* a comment */
            ;
        else if (buffer[0] == '-' && buffer[1] != 0) {
            DeleteString(t, buffer+1);
            j++;
        }
        else {
            LoadString(t, buffer);
            i++;
        }
    }

    fclose(infile);
    printf("Loaded %d items and deleted %d from %s.\n",
        i, j, fname);
}

/*
 * A sample action function: it prints out the data
 * at each node along with the node's level in the tree
 */
int ShowFunc(void *m, int level)
{

   fprintf(stdout, "%s (%d)\n",
        ((Mynode *)m) -> text, level);

    return TREE_OK;
}


/*
 * A pair of functions to print the tree as a diagram.
 */

#if !defined(ALTDRAW)
  #define TOP '+'  //yyw
  #define BOT '+'  //yyw
  #define HOR '-' //yyw
  #define VRT '|' //yyw
  //#define TOP '?
  //#define BOT '?
  //#define HOR '?
  //#define VRT '?
#else
  #define TOP '/'
  #define BOT '\\'
  #define HOR '-'
  #define VRT '|'
#endif



#define DRAWBUF 100
char draw[DRAWBUF];
char work[DRAWBUF * 2];
int maxdepth;
FILE *outfile;

void xrWalk(Bnode *n, int level)
{
    int i;

    if (n != NULL) {
        /* Monitor */
        if (level > maxdepth)
            maxdepth = level;

        /*
         * Go right
         */
        draw[level * 2] = TOP;
        draw[level * 2 + 1] = ' ';
        xrWalk(n -> link[RIGHT], level + 1);

        /*
         * Show current node
         */
        strncpy(work, draw, level * 2);
        if (level > 0) {
            int c;

            c = work[0];
            for (i = 2; i < level * 2; i += 2)
                if (work[i] == c)
                    work[i - 2] = ' ';
                else
                    c = work[i];

            work[level * 2 - 1] =
                HOR;

            for (i = 0; i < level * 2 - 2; i += 2)
                if (work[i] != ' ') {
                    work[i] = VRT;
                }
        }

        sprintf(work + level * 2, "%s (%d)",
                            ((Mynode *)n)->text, level);
        fputs(work, outfile);

        fputs("\n", outfile);

        /*
         * Go left
         */
        draw[level * 2] = BOT;
        draw[level * 2 + 1] = ' ';
        xrWalk(n -> link[LEFT], level + 1);

    }
}

int xWalkBintree(Bintree *t, char *name, char *mode)
{
    if (t -> DummyHead -> link[RIGHT] == NULL) {
        fputs("Empty tree\n", stdout);
        return TREE_FAIL;
    }

    maxdepth = -1;

    outfile = stdout;
    if (name) {
        outfile = fopen(name, mode);
        if (outfile == NULL) {
            fprintf(stdout, "Can't open %s.\n", name);
            name = NULL;
            outfile = stdout;
        }
    }

    xrWalk(t -> DummyHead -> link[RIGHT], 0);
    fprintf(outfile, "Max depth %d.\n", maxdepth);

    if (name)
        fclose(outfile); /* a real file */
    else
        fflush(outfile); /* stdout */

    return TREE_OK;
}

int compare_length = 0;
int CompareFunc(void *n1, void *n2)
{
    if (compare_length)
        return strncmp(((Mynode *)n1)->text,
        ((Mynode *)n2)->text,
        compare_length);
    else
        return strcmp(((Mynode *)n1)->text,
        ((Mynode *)n2)->text);
}


/*
	@desc 二叉树的非递归中序遍历
 */
int WalkBintreeByStack(Bintree *t, DoFunc df)//yyw
{
	struct StkElement *stk_el;  /* scratch stack element */
	Stack *stk;                 /* the stack we will use */

	// 创建栈
	stk = CreateStack(40);   /* create a stack of 40 items */
	if (stk == NULL) {
		fprintf(stderr, "Insufficient Memory\n");
		exit(EXIT_FAILURE);
	}
	// 创建栈的临时节点
	stk_el = (struct StkElement *)
		malloc(sizeof(struct StkElement));
	if (stk_el == NULL) {
		fprintf(stderr, "Insufficient memory\n");
		exit(EXIT_FAILURE);
	}

	Mynode *temp = t->DummyHead->link[RIGHT];
	memcpy(stk_el->link, temp, sizeof(struct sMynode *));
	PushElement(stk, stk_el);

	int level = 0;
	while (temp != NULL || stk->top > -1 ) {
		while (temp != NULL) {
			memcpy(stk_el->link, temp, sizeof(struct sMynode *));
			PushElement(stk, stk_el);

			temp = temp->link[LEFT];
		}
		
		PopElement(stk, stk_el);
		temp = (struct sMynode *)stk_el->link;
		df(temp, level);
		printf("%s\n", temp->text);
		temp = temp->link[RIGHT];
	}
	

}

main(int argc, char **argv)
{
    char inbuf[BUFLEN], *s;
    Bintree *tree;
    Mynode *dummy;

    /* create a dummy node for the tree algorithms */
    dummy = (Mynode *) InitBintreeNode(sizeof(Mynode));
    dummy->text[0] = 0; /* must contain valid data */

    /* create a tree */
    tree = NewBintree((Bnode *) dummy,
                        CompareFunc, 1, sizeof(Mynode));

    for (;;) {
        fputs("Action (? for help): ", stdout);
        fflush(stdout);
        fgets(inbuf, BUFLEN, stdin);
        s = inbuf + strlen(inbuf);
        while(iscntrl(*s))
            *s-- = 0;

        switch (inbuf[0]) {
            case '?':
                fputs(
                    "@file     - Load strings in file to tree\n"
                    "d string  - Delete string from tree\n"
                    "dup [0|1] - Disallow/allow duplicates\n"
                    "s [file]  - Display tree (overwrite file)\n"
                    "w         - Walk tree, running ShowFunc()\n"
					"t         - Walk tree By Stack, running ShowFunc()\n"
                    "q         - Quit\n"
                    , stdout);
                fflush(stdout);
                break;

            case '@':
                LoadFile(tree, inbuf + 1);
                break;

            case 'd':
                if (inbuf[1] == 'u' && inbuf[2] == 'p') {
                    if (inbuf[3] == ' ' &&
                        (inbuf[4] == '0' || inbuf[4] == '1'))
                        tree -> DuplicatesOk =
                        inbuf[4] == '0' ? 0 : 1;
                    fputs("Duplicates are ", stdout);
                    if (tree -> DuplicatesOk == 0)
                        fputs("not ", stdout);
                    fputs("allowed.\n", stdout);
                    break;
                }

                if (inbuf[1] != ' ' || inbuf[2] == 0)
                    fputs(" Not a valid command\n", stdout);


                else
                    DeleteString(tree, inbuf + 2);
                break;


            case 's': 
                if (inbuf[1] == ' ' && inbuf[2] != 0)
                    xWalkBintree(tree, inbuf + 2,
                    inbuf[0] == 's' ? "w" : "a");
                else
                    xWalkBintree(tree, NULL, NULL);
                break;

            case 'w':
                WalkBintree(tree, ShowFunc);
                break;

			case 't':
                WalkBintreeByStack(tree, ShowFunc);
                break;

            case 'q':
                return;

            case ';':
                break;  /* comment */

            default:
                fputs(" Not a valid command\n", stdout);
                break;
        }
    }
}
#endif
