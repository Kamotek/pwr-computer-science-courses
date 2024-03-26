from sys import stdin


import record_parser




def main():
    records = record_parser.parse(stdin.readlines())
    


if __name__ == "__main__":
    main()
