from datetime import datetime


def filter_hour(records, start_hour, end_hour):
    filtered = []
    for record in records:
        date_format = "%d/%b/%Y:%H:%M:%S"
        hour = datetime.strptime(record['date'], date_format).hour

        is_within = False
        if start_hour <= end_hour:
            if start_hour <= hour < end_hour:
                is_within = True
            else:
                is_within = False

        else:
            if hour >= start_hour or hour < end_hour:
                is_within = True
            else:
                is_within = False

        if is_within:
            filtered.append(record)

    return filtered


def show_within_hours(records):
    filtered = filter_hour(records, 22, 6)
    for record in filtered:
        print(record['raw_string'])

if __name__ == "__main__":
    from sys import stdin
    import record_parser

    records = record_parser.parse(stdin.readlines())
    show_within_hours(records)
