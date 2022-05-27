import random

def generateRandomLine(section, days, timesMWF, timesTR, periods):
    MWF = [True, False]
    randomLine = ""

    # section = random.choice(sections)
    day = random.choice(days)

    threeDays = random.choice(MWF)
    if threeDays:
        time = random.choice(timesMWF)
    else:
        time = random.choice(timesTR)

    period = random.choice(periods)

    randomLine = """#%s#%s#%s#%s""" % (section, day, time, period)
    return randomLine


def generateNewLines(filename):
    lines = []
    curr_faculty = "None"
    with open(inputfilename, 'r') as file:
        for line in file:
            line = line.strip()

            if line[0] == '$':
                curr_faculty = line[1:]
            else:
                for section in sections:
                    newLine = curr_faculty + "#" + line + generateRandomLine(section, days, timesMWF, timesTR, periods)
                    lines.append(newLine)

    return lines


def generateSQLStatements(lines, tablename):
    statements = []

    for line in lines:
        separated_lines = line.split("#")

        if len(separated_lines) == 1:
            continue

        # faculty_name        = separated_lines[0] -> str
        # course_id           = separated_lines[1] -> str
        # course_name         = separated_lines[2] -> str
        # credit_hours        = separated_lines[3] -> int
        # section             = separated_lines[4] -> str
        # days                = separated_lines[5] -> str
        # time                = separated_lines[6] -> hour:minute-hour:minute
        # period              = separated_lines[7] -> month:day-month:day

        statement = '''INSERT INTO %s VALUES (\'%s\', \'%s\', \'%s\', %s, \'%s\', \'%s\', \'%s\', \'%s\');''' % (
            tablename, separated_lines[0], 
            separated_lines[1], separated_lines[2], 
            separated_lines[3], separated_lines[4], 
            separated_lines[5], separated_lines[6], separated_lines[7]
        )
        statements.append(statement)

    return statements


sections = ["A01", "A02", "A03"]
days = ["MWF","TR"]
timesMWF = [
    "09:30-10:20",
    "10:30-11:20",
    "11:30-12:20",
    "12:30-13:20",
    "14:30-15:20"
    ]
timesTR = [
    "09:30-10:20",
    "10:30-11:20",
    "11:30-12:20",
    "12:30-13:20",
    "14:30-15:20"
    ]
periods = ["2021/01/18-2021/04/18"]

inputfilename = 'raw_courses.txt'
tablename = "courses"

newLines = generateNewLines(inputfilename)
statements = generateSQLStatements(newLines, tablename)

for s in statements:
    print(s)
