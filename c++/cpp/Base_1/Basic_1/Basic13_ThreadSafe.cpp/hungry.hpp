#include <iostream>

using namespace std;

/*
饿汉模式实现单例

*/

// 1、定义一个单例类
template <class T>
class singleton {
protected:
	singleton() {};

private:
	// 禁用拷贝构造函数
	singleton(const singleton&) {};
	// 禁用赋值符
	singleton& operator=(const singleton^&) {};

	static T* m_instance; // 单例对象

public:
	static T* GetInstance();
};

// 2、获取单例对象
template <class T>
T* singleton<T>::GetInstance() {
	return m_instance;
}

// 3、在类加载的时候，就将单例对象初始化
template <class T>
T* singleton<T>::m_instance = new T();