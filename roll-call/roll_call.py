#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""简单点名系统：自己添加姓名，最多 10 人，随机点名。"""

from __future__ import annotations

import json
import random
import sys
import time
from pathlib import Path

MAX_NAMES = 10
DATA_FILE = Path(__file__).with_name("names.json")


def load_names() -> list[str]:
    if not DATA_FILE.exists():
        return []
    try:
        data = json.loads(DATA_FILE.read_text(encoding="utf-8"))
    except (json.JSONDecodeError, OSError):
        return []
    if not isinstance(data, list):
        return []
    return [str(n).strip() for n in data if str(n).strip()][:MAX_NAMES]


def save_names(names: list[str]) -> None:
    DATA_FILE.write_text(
        json.dumps(names, ensure_ascii=False, indent=2),
        encoding="utf-8",
    )


def show_menu(count: int) -> None:
    print()
    print("=" * 36)
    print("          简 单 点 名 系 统")
    print("=" * 36)
    print(f"  当前名单：{count} / {MAX_NAMES} 人")
    print("  1. 添加姓名")
    print("  2. 查看名单")
    print("  3. 开始点名")
    print("  4. 删除姓名")
    print("  5. 清空名单")
    print("  0. 退出")
    print("-" * 36)


def add_name(names: list[str]) -> None:
    if len(names) >= MAX_NAMES:
        print(f"名单已满（最多 {MAX_NAMES} 人），请先删除后再添加。")
        return

    name = input("请输入姓名：").strip()
    if not name:
        print("姓名不能为空。")
        return
    if name in names:
        print(f"「{name}」已在名单中。")
        return

    names.append(name)
    save_names(names)
    print(f"已添加「{name}」，当前 {len(names)} / {MAX_NAMES} 人。")


def list_names(names: list[str]) -> None:
    if not names:
        print("名单为空，请先添加姓名。")
        return
    print("当前名单：")
    for i, name in enumerate(names, start=1):
        print(f"  {i}. {name}")


def roll_call(names: list[str]) -> None:
    if not names:
        print("名单为空，请先添加姓名。")
        return

    print("正在点名", end="", flush=True)
    last = ""
    for _ in range(18):
        candidate = random.choice(names)
        if len(names) > 1 and candidate == last:
            continue
        last = candidate
        sys.stdout.write(f"\r正在点名… {candidate}   ")
        sys.stdout.flush()
        time.sleep(0.08)

    picked = random.choice(names)
    sys.stdout.write("\r" + " " * 40 + "\r")
    print(f"★ 点到：{picked}")


def remove_name(names: list[str]) -> None:
    if not names:
        print("名单为空。")
        return

    list_names(names)
    raw = input("请输入要删除的序号：").strip()
    if not raw.isdigit():
        print("请输入有效序号。")
        return
    index = int(raw)
    if index < 1 or index > len(names):
        print("序号超出范围。")
        return

    removed = names.pop(index - 1)
    save_names(names)
    print(f"已删除「{removed}」。")


def clear_names(names: list[str]) -> None:
    if not names:
        print("名单已经是空的。")
        return
    confirm = input("确认清空全部名单？(y/N)：").strip().lower()
    if confirm != "y":
        print("已取消。")
        return
    names.clear()
    save_names(names)
    print("名单已清空。")


def main() -> None:
    names = load_names()
    actions = {
        "1": add_name,
        "2": list_names,
        "3": roll_call,
        "4": remove_name,
        "5": clear_names,
    }

    while True:
        show_menu(len(names))
        choice = input("请选择操作：").strip()
        if choice == "0":
            print("再见！")
            break
        action = actions.get(choice)
        if action is None:
            print("无效选项，请重新选择。")
            continue
        action(names)


if __name__ == "__main__":
    try:
        main()
    except KeyboardInterrupt:
        print("\n已退出。")
        sys.exit(0)
