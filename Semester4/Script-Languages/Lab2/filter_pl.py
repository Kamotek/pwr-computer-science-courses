def filter_records_by_domain(records, domain):
    filtered_records = []
    for record in records:
        if record['address'].endswith(domain):
            filtered_records.append(record)
    return filtered_records


def print_filtered_records(records):
    domain = ".pl"
    filtered_records = filter_records_by_domain(records, domain)
    for record in filtered_records:
        print(record['raw_string'])
    

if __name__ == "__main__":
    from sys import stdin
    import record_parser

    records = record_parser.parse(stdin.readlines())
    print_filtered_records(records)