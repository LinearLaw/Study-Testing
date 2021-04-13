#include <iostream>
#include <winsock.h>

#pragma comment(lib, "ws2_32.lib")

using namespace std;

void initialization();


int main()
{
	int send_len = 0;
	int recv_len = 0;
	int len = 0;

	char send_buf[100];
	char recv_buf[100];

	SOCKET s_server;
	SOCKET s_accept;

	SOCKADDR_IN server_addr;
	SOCKADDR_IN accept_addr;
	initialization();

	server_addr.sin_family = AF_INET;
	server_addr.sin_addr.S_un.S_addr = htonl(INADDR_ANY);
	server_addr.sin_port = htons(5010);

	s_server = socket(AF_INET, SOCK_STREAM, 0);

	if (bind(s_server, (SOCKADDR *)&server_addr, sizeof(SOCKADDR))
		== SOCKET_ERROR	
	) {
		cout << "[Fail] Socket bind error" << endl;
	}
	else {
		cout << "[Success] Socket bind success" << endl;
	}

	if (listen(s_server, SOMAXCONN) < 0) {
		cout << "[Fail] 设置监听状态失败" << endl;
		WSACleanup();
	}
	else {
		cout << "[Success] 设置监听状态成功" << endl;
	}
	cout << "服务器正在监听连接，请稍后..." << endl;
	cout << endl;

	len = sizeof(SOCKADDR);
	s_accept = accept(s_server, (SOCKADDR *)&accept_addr, &len);
	if (s_accept == SOCKET_ERROR) {
		cout << "[Fail] 连接失败" << endl;
		WSACleanup();
		return 0;
	}

	cout << "连接已建立，准备接受数据" << endl;
	while (1) {
		recv_len = recv(s_accept, recv_buf, 100, 0);
		if(recv_len < 0){
			cout << "[Fail] 接收失败" << endl;
			break;
		}
		else {
			cout << "[Client]：" << recv_buf << endl;
		}

		cout << "[Server]：";
		cin >> send_buf;
		send_len = send(s_accept, send_buf, 100, 0);
		if (send_len < 0) {
			cout << "[Fail] 发送失败" << endl;
			break;
		}
	}

	closesocket(s_server);
	closesocket(s_accept);

	WSACleanup();
	return 0;
}

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