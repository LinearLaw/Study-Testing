#include <iostream>
#include <pthread.h>

using namespace std;

/*
懒汉式，需要考虑线程安全

*/
template<class T>
class singleton {
protected:
	singleton() {};

private:
	singleton(const singleton&) {};
	singleton& operator=(const singleton&) { };

	// 单例对象
	static T* m_instance;
	// 锁
	static pthread_mutex_t mutex;

public:
	static T* GetInstance();
};

template<class T>
T* singleton<T>::GetInstance() {

	/* 这里有问题，每次进来都要加锁，影响效率 */
	pthread_mutex_lock(&mutex);
	if (m_instance == NULL) {
		m_instance = new T();
	}
	pthread_mutex_lock(&mutex);

	/* 改进，使用双检锁	
	*/



	return m_instance;
}

template<class T>
pthread_mutex_t singleton<T>::mutex = PTHREAD_MUTEX_INITIALIZER;

template <class T>
T* singleton<T>::m_instance = NULL;
