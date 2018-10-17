#!/usr/bin/env python
import sys

d = {}

chars = [chr(i) for i in range(ord('а'), ord('я'))]
for cur in chars:
    d[cur] = {}
    for nxt in chars:
        d[cur][nxt] = 0

for word in sys.stdin:
    for (cur, nxt) in zip(word, word[1:]):
        if cur in d:
            if nxt in d[cur]:
                d[cur][nxt] += 1

for cur in d.keys():
    total = sum(d[cur].values())
    for nxt in d[cur]:
        print(cur, nxt, d[cur][nxt] / total)
