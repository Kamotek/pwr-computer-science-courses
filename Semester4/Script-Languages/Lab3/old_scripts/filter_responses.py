def filter_responses(records, codes: list[int]):
    filtered = []
    for record in records:
        if record['code'] in codes:
            filtered.append(record)

    return filtered


def show_with_codes(records):
    filtered = filter_responses(records, [200])
    for record in filtered:
        print(record['raw_string'])

if __name__ == "__main__":
    from sys import stdin
    import record_parser

    records = record_parser.parse(stdin.readlines())
    show_with_codes(records)