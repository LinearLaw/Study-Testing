/*--- bintree.h --------------------------- Listing 6-2 ---------
 * Binary-tree definitions
 *
 *-------------------------------------------------------------*/

#define TREE_OK    (0)
#define TREE_FAIL (-1)
#define LEFT        1
#define RIGHT       0


typedef struct sBnode {
   struct sBnode *link[2];
} Bnode;

/* Our binary tree is made up of these */
typedef struct sMynode {
    /* A copy of the items in a Bnode */
    struct sMynode *link[2];
    char text[20];
} Mynode;

/* Control structure for a binary tree */
typedef int (*CompFunc) (void *node1, void *node2);
typedef int (*DoFunc) (void *node, int level);

typedef struct sBintree {
    Bnode *DummyHead;
    CompFunc Compare;
    int DuplicatesOk;
    size_t NodeSize;
} Bintree;

/* Prototypes */
Bintree *NewBintree (Bnode *dummy, CompFunc cf,
                     int dup_ok, size_t node_size);
Bnode   *FindBintree(Bintree *t, Bnode *n);
int      InsBintree (Bintree *t, Bnode *n);
Bnode   *DelBintree (Bintree *t, Bnode *n);
int      WalkBintree(Bintree *t, DoFunc df);
Bnode   *InitBintreeNode(size_t size);
int      WalkBintreeByStack(Bintree *t, DoFunc df);//to do..