def count(records):
    counts = {}
    for record in records:
        code = record['code']
        if code in counts:
            counts[code] += 1
        else:
            counts[code] = 0

    return counts


def show_counts(records):
    queried_codes = (200, 302, 404)
    counts = count(records)
    for code in queried_codes:
        if code in counts:
            print(f"Code {code}: {counts[code]}")
        else:
            print(f"Code {code}: 0")
