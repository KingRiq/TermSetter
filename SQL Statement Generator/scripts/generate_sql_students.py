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

        # student_id           = separated_lines[0] -> str
        # student_name         = separated_lines[1] -> str
        # student_password     = separated_lines[2] -> str
        # student_email        = separated_lines[3] -> str
        # student_phone        = separated_lines[4] -> str

        statement = '''INSERT INTO %s (student_id, student_name, student_password, student_email, student_phone) VALUES (\'%s\', \'%s\', \'%s\', \'%s\', \'%s\');''' % (
            tablename, separated_lines[0], 
            separated_lines[1], separated_lines[2],
            separated_lines[3], separated_lines[4]
        )
        statements.append(statement)

    return statements


inputfilename = 'raw_students.txt'
tablename = "students"

newLines = generateNewLines(inputfilename)
statements = generateSQLStatements(newLines, tablename)

for s in statements:
    print(s)
