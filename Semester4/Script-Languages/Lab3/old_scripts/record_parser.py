from sys import stderr


def parse(records: list[str]):
    parsed_records = []
    for text_record in records:
        raw_values = text_record.split()

        try:
            record = {"raw_string" : text_record[:-1],
                      'address': raw_values[0], 'date': raw_values[3][1:], 'timezone': raw_values[4][:-1],
                      'request_type': raw_values[5][1:], 'path': raw_values[6],
                      'http_version': raw_values[7][:-1] if not raw_values[7].isnumeric() else None,
                      'code': int(raw_values[-2]),
                      'bytes': int(raw_values[-1]) if raw_values[-1] != '-' else 0
                      }

            parsed_records.append(record)

        except TypeError:
            print(f"'Error while parsing line' {raw_values}. Invalid format.", file=stderr)

    return parsed_records
