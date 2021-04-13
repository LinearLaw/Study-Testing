#include <iostream>
#include <winsock.h>

#pragma comment(lib, "ws2_32.lib")

using namespace std;

void initialization();

struct S{};


int main()
{	
	cout << "SIZE:" << sizeof(S) << endl;
	cout << endl;

	/* 	发送数据的长度	
		接收数据的长度
		SOCKADDR的大小
	 */
	int send_len = 0;
	int recv_len = 0;
	int len = 0;

	/*	接收和发送数据的字符缓冲区 */
	char send_buf[100];
	char recv_buf[100];

	/*	定义服务端的Socket和接受请求的Socket*/
	SOCKET s_server;
	SOCKET s_accept;

	/*	服务端地址，接收请求地址*/
	SOCKADDR_IN server_addr;
	SOCKADDR_IN accept_addr;
	initialization();

	/*
		服务端信息
	 */
	server_addr.sin_family = AF_INET;
	server_addr.sin_addr.S_un.S_addr = htonl(INADDR_ANY);
	server_addr.sin_port = htons(5010);

	/*	创建socket并bind */
	s_server = socket(AF_INET, SOCK_STREAM, 0);
	if (bind(s_server, (SOCKADDR *)&server_addr, sizeof(SOCKADDR))
		== SOCKET_ERROR	
	) {
		cout << "[Fail] Socket bind error" << endl;
	}
	else {
		cout << "[Success] Socket bind success" << endl;
	}

	/*	设置socket监听 */
	if (listen(s_server, SOMAXCONN) < 0) {
		cout << "[Fail] 设置监听状态失败" << endl;
		WSACleanup();
	}
	else {
		cout << "[Success] 设置监听状态成功" << endl;
	}
	cout << "服务器正在监听连接，请稍后..." << endl;
	cout << endl;

	/*	接收连接请求  */
	len = sizeof(SOCKADDR);
	s_accept = accept(s_server, (SOCKADDR *)&accept_addr, &len);
	if (s_accept == SOCKET_ERROR) {
		cout << "[Fail] 连接失败" << endl;
		WSACleanup();
		return 0;
	}

	cout << "连接已建立，准备接受数据" << endl;
	while (1) {
		/*	等待接收由Client发送来的数据，  */
		recv_len = recv(s_accept, recv_buf, 100, 0);
		if(recv_len < 0){
			cout << "[Fail] 接收失败" << endl;
			break;
		}
		else {
			cout << "[Client]：" << recv_buf << endl;
		}

		/*	输入要发送的内容，返回结果 */
		cout << "[Server]：";
		cin >> send_buf;
		send_len = send(s_accept, send_buf, 100, 0);
		if (send_len < 0) {
			cout << "[Fail] 发送失败" << endl;
			break;
		}
	}

	/*  关闭socket  */
	closesocket(s_server);
	closesocket(s_accept);
	/*  释放DLL资源  */
	WSACleanup();
	return 0;
}

/*
	初始化 Socket 库
*/
void initialization() {
	WORD w_req = MAKEWORD(2, 2);
	WSADATA wsadata;

	int err = WSAStartup(w_req, &wsadata);
	if (err != 0) {
		cout << "[Fail] 初始化Socket库失败" << endl;
	}
	else {
		cout << "[Success] 初始化Socket库成功" << endl;
	}


	if (LOBYTE(wsadata.wVersion) !=2 || HIBYTE(wsadata.wHighVersion) != 2) {
		cout << "[Fail] Socket库版本号错误" << endl;
		WSACleanup();
	}
	else {
		cout << "[Success] Soket库版本号正确" << endl;
	}
}