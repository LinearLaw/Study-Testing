function print(str){
    console.log(str)
}
function calc(line) {
    let res = 0;
    let stack = 0;

    let i = 0
    while (i < line.length) {
        while (i < line.length && line[i] != '<') {
            i++;
        }

        let temp = ''
        while (i < line.length && line[i] != '>') {
            temp += line[i];
            i++;
        }
        // 当前 line[i] == '>'
        if (temp.length > 0) {
            temp += line[i]
            if (temp == '<div>') {
                stack++;
            } else if (temp == '</div>') {
                if (stack > 0) {
                    stack--;
                    res++;
                }
            }
        }
        i++;
    }
    return res
}

// console.log(calc("<div>hello</div>mi<div>xiao<div>you</div></div>"))



// v2v
function v2vAct(v2v){
    this.hp = v2v[0]
    this.att = v2v[1]
    this.def = v2v[2]
    this.spd = v2v[3]
    
    this.status = ''
    this.isAttAdd = false
}
v2vAct.prototype.a = function(kalpas){
    let temp = this.att - kalpas.def
    kalpas.hp -= (temp>0 ? temp : 0)
}
// 主动技能
v2vAct.prototype.q = function(kalpas){
    // 攻击对手
    let temp = this.att - kalpas.def
    kalpas.hp -= (temp>0 ? temp : 0)
    
    // 令对手混乱
    kalpas.status = 'chaos'
}
// 被动技能
v2vAct.prototype.e = function(kalpas){
    if(this.hp < 31){
        // 自身和对手都恢复20点生命
        this.hp += 20
        kalpas.hp += 20
        
        // 攻击力上升，只能触发一次
        if(!this.isAttAdd){
            this.att += 15
            this.isAttAdd = true
        }
    }
}

// kalpas
function kalpasAct(kalpas){
    this.maxHp = kalpas[0] // 初始生命值
    this.hp = kalpas[0]
    
    this.baseAtt = kalpas[1] // 基础攻击
    this.att = kalpas[1]
    
    this.def = kalpas[2]
    this.spd = kalpas[3]
    /* 'chaos' = 本次普通攻击将会攻击自己
    */
    this.status = ''
    // 主动技能下次可用的回合数
    this.useTimes = 0
    // 休息的回合
    this.relaxTimes = 0
}
kalpasAct.prototype.a = function(v2v){
    if(this.status == 'chaos'){
        let temp = this.att - this.def
        this.hp -= (temp>0 ? temp : 0)
        this.status = ''
    }else{
        let temp = this.att - v2v.def
        v2v.hp -= (temp>0 ? temp : 0)
    }
}
// 主动技能，每三回合发动
kalpasAct.prototype.q = function(v2v, times){
    if(this.useTimes > times){
        // 普通攻击
        this.a(v2v)
        return
    }
    // hp < 11 无法发动主动技能
    if(this.hp >= 11){
        // 燃烧自己10点生命
        this.hp -= 10
        // 给对手45伤害 + 20元素伤害，元素伤害无视防御
        let temp = 45 - v2v.def
        v2v.hp -= (temp > 0 ? temp : 0)
        v2v.hp -= 20
    }else{
        // 普通攻击
        this.a(v2v)
    }
    
    // 主动技能进入CD一回合
    this.useTimes = times + 2
    // 下一回合休息
    this.relaxTimes = times + 1
}
// 被动技能，加攻击
kalpasAct.prototype.e = function(){
    let catt = (this.maxHp - this.hp) / 5
    this.att = parseInt(this.baseAtt + catt)
}


function A(tt1, tt2) {
    let t1 = tt1.split(' ')
    let t2 = tt2.split(' ')
    // 维尔薇
    let v2varr = t1.map(item=>parseInt(item))
    // 千劫
    let kalpasarr = t2.map(item=>parseInt(item))
    
    let v2v = new v2vAct(v2varr)
    let kalpas = new kalpasAct(kalpasarr)

    // 无法破防 -> 可能导致超时
    if(v2v.def >= kalpas.att){
        print('I love V2V forever!')
    }else if(v2v.att <= kalpas.def){
        print('Kalpas yame te!')
    }
    
    let times = 1; // 回合数
    while(v2v.hp>0 && kalpas.hp>0){
        if(v2v.spd > kalpas.spd){
            // v2v先动手
            // 被动发动
            v2v.e(kalpas)
            if(times % 3 == 0){
                // 主动技能
                v2v.q(kalpas)
            }else{
                v2v.a(kalpas)
            }
            if(kalpas.hp<=0 || v2v.hp<=0){
                break;
            }
            // kalpas动手
            kalpas.e()
            if(times % 3 == 0){
                // 主动技能
                kalpas.q(v2v, times)
            }else{
                kalpas.a(v2v)
            }
        }else{
            // kalpas动手
            if(kalpas.relaxTimes != times){
                kalpas.e()
                if(times % 3 == 0){
                    // 主动技能
                    kalpas.q(v2v, times)
                }else{
                    kalpas.a(v2v)
                }
            }
            if(kalpas.hp<=0 || v2v.hp<=0){
                break;
            }
            // v2v
            v2v.e(kalpas)
            if(times % 3 == 0){
                // 主动技能
                v2v.q(kalpas)
            }else{
                v2v.a(kalpas)
            }
        }
        times++
    }
    /**
    I love V2V forever!
    Kalpas yame te!
    I love V2V forever!
    */
    if(v2v.hp>0){
        print('I love V2V forever!')
    }else if(kalpas.hp > 0){
        print('Kalpas yame te!')
    }
}
/**
3
100 100 100 100
100 1 1 1
100 100 1 100
100 100 1 1
100 20 12 25
100 23 9 26

I love V2V forever!
Kalpas yame te!
I love V2V forever!
 */
// A("100 20 12 25", "100 23 9 26")
// A("100 100 100 100", "100 1 1 1")
// A("100 100 1 100", "100 100 1 1")
A("100 1 1 1", "100 2 1 1")
