from functools import singledispatch
from datetime import datetime

@singledispatch
def report(value):
    return f"raw: {value}"

@report.register
def _(value: datetime):
    return f"dt: {value.isoformat()}"

@report.register
def _(value: complex):
    return f"complex: {value.real}{value.imag:+}j"

print(report(datetime.now()))
print(report(100-30j))
print(report('test'))

print(report.registry)