#include <iostream>  

using namespace std;

int main(void)
{
	int a, b;

	cin >> a >> b;
	if (a < 1 || a>9 || b < 1 || b>9)
		return -1;
	cout << a + b << endl;
	return 0;
    
}