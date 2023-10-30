import zipfile

def extractFile(zFile, password):
    try:
        zFile.extractall(pwd=password)
        return password
    except:
        print('wrong pass')
        return

def main():
    zFile = zipfile.ZipFile('test.zip')
    passFile = open('passlist.txt')
    for line in passFile.readlines():
        password = line.strip('/n')
        guess = extractFile(zFile, password)
        if guess:
            print('Password = ' + password)
            break

if __name__ == '__main__':
    main()
