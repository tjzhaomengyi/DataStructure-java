import sys

for line in sys.stdin:
    line = line.strip()
    if not line:  # 跳过为空的行
        continue
    a, b = map(int, line.split())
    print(a + b)

