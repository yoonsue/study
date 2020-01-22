#include <iostream>
using namespace std;

int main()
{
    int a = 1;
    int b = 0;
    cout << "short int size " << sizeof(short int) << "byte\n";
    cout << "int size " << sizeof(int) << "byte\n";
    cout << "long int size " << sizeof(long int) << "byte\n";
    cout << "float size " << sizeof(float) << "byte\n";
    cout << "double size " << sizeof(double) << "byte\n";
    cout << "long double size " << sizeof(long double) << "byte\n";
    cout << "var a size " << sizeof(a) << "byte\n";
    cout << "operation a+b size " << sizeof(a+b) << "byte\n";

    return 0;
}