#include <iostream>
using namespace std;

template <class T>
T maxt(T x, T y)
{
    if (x>y)
        return x;
    else 
        return y;
}

int main()
{
    int a, b;
    double da, db;

    cout << "Input two integer.\n";
    cin >> a >> b;

    cout << "Input two double.\n";
    cin >> da >> db;

    int ans1 = maxt(a, b);
    double ans2 = maxt(da, db);

    cout << "Integer max: " << ans1 << ".\n";
    cout << "Double max: " << ans2 << ".\n";

    return 0;
}