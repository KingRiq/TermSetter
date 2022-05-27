import random

def generateNewLines(filename):
    lines = []
    with open(inputfilename, 'r') as file:
        for line in file:
            line = line.strip()
            lines.append(line)
    return lines


def generateSQLStatements(lines, tablename):
    statements = []

    for line in lines:
        separated_lines = line.split("#")

        if len(separated_lines) == 1:
            continue

        # course_id           = separated_lines[0] -> str
        # stuent_id           = separated_lines[1] -> str

        statement = '''INSERT INTO %s (course_id, student_id) VALUES (\'%s\', \'%s\');''' % (
            tablename, separated_lines[0], separated_lines[1]
        )
        statements.append(statement)

    return statements

inputfilename = 'raw_enrollment.txt'
tablename = "enrollments"

newLines = generateNewLines(inputfilename)
statements = generateSQLStatements(newLines, tablename)

for s in statements:
    print(s)
