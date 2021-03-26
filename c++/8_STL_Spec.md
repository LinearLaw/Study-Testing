# priority_queue

优先队列
```cpp
#include <vector>
#include <queue>     // 优先队列在头文件queue中 
#include <functional>   // 用来引入less和greater

// 第三个参数不传的时候，默认是大根堆
priority_queue<int,vector<int>> big_heap0;


// 创建一个大根堆
priority_queue<int,vector<int>,less<int>> big_heap;

// 创建一个小根堆
priority_queue<int,vector<int>,greater<int>> small_heap;

```

- bool empty() const
- int size() const
- void pop()
- int top()
- void push(int arg)