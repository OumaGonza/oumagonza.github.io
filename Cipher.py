from collections import deque

def main():
    """
    - Queries input from user to be used in encryption and decryption.
    - Executes encryption and decryption
    - Prints results to console
    """
    print("########################")
    print("#                      #")
    print("#    FEISTEL CIPHER    #")
    print("#                      #")
    print("########################\n")

    print("NOTE: Remember the codeword and number you supply. You will be prompted for it again.\n")
    print("Please enter your codeword (at least 8 characters)")
    codeword = input("Codeword: ")
    print("\nPlease enter your numerical key code (code > 0)")
    seed = input("Key code: ")
    seed = int(seed)
    print("\nEnter plaintext to encrypt (at least 8 characters) ")
    text = input("Plaintext: ")

    blocksize = 64
    rounds = 32
    key = tobits(codeword)
    subkeys = subkeyGenerator(key, seed, rounds)

    encryptedBlocks = encryption(text, blocksize, rounds, subkeys)

    print("\nEncrypted text given in bits:")
    for blocks in encryptedBlocks:
        print("".join(str(n) for n in blocks), end="")
    print("\n")

    print("To decrypt the message, please enter the ciphertext in bits (avoid any spaces in the input")
    encryptedbits = input("Ciphertext:")
    encryptedbits = tobits(encryptedbits)

    print("\nPlease re-enter your codeword")
    codeword = input("Codeword: ")
    print("\nRe-enter the same numerical key code (code > 0)")
    seed = input("Key code: ")
    seed = int(seed)
    key = tobits(codeword)
    subkeys = subkeyGenerator(key, seed, rounds)

    decryptedBits = decryption(encryptedbits, blocksize, rounds, subkeys)

    try:
            print("\nPlaintext: {}".format(frombits(decryptedBits, 8)), end="")
    except UnicodeEncodeError:
            print("\nERROR: Decryption failed\n")
    print("\n")
    input("Press any key to quit")
    return


def convert_string_to_bits(string):
    """
    Converts each character in a string to bits, and returns a list of binary integers.
    Modified to fit this implementation and this is a sample text
    """
    result = []
    for c in string:
       bits = bin(ord(c))[2:]
       bits = '00000000'[len(bits):] + bits  # since all characters are 8 bit, bits is set to '00000000'
       result.extend([int(b) for b in bits])

    return result


def tobits(string):
    """ Determines the format of input (string or int) and then the string is converted."""
    result = []

    # text is ints
    try:
        if int(string):

            # text is int, but not binary
            if not all(x in "01" for x in string):

                result = convert_string_to_bits(string)
                return result

            # text is binary
            for b in string:
                result.append(int(b))
            return result

    # text is not binary
    except ValueError:
        pass

    # each character in 's' is converted to 8-bit block which is appended to the result
    result = convert_string_to_bits(string)

    return result


def frombits(block, blocksize):
    """
    Takes in a block of arbitrary length and converts blocks of 8 bit to characters which is then returned.
    """

    chars = []
    try:
        for b in range(len(block) // blocksize):
            byte = block[b*blocksize:(b+1)*blocksize]
            chars.append(chr(int(''.join([str(bit) for bit in byte]), 2)))

    except UnicodeEncodeError:
        print("'N/A'")

    return ''.join(chars)


def p32box(key):
    """
    Takes a 32 bit key as input, permutes each bit and then returns the new key
    Permutation boxes is discussed on Page 3 of report.
    """
    perm = list([32, 24, 11, 31, 13, 8, 19, 22,
                 12, 3, 4, 29, 21, 10, 28, 26,
                 30, 23, 7, 16, 20, 17, 18, 2,
                 6, 25, 5, 15, 9, 14, 27, 1])

    newKey = list([0]*32)

    for i in range(0,32):
        newKey[i] = key[perm[i]-1]

    return newKey


def pc1(key):
    """Permutation Choice 1. Permutation boxes is discussed on Page 3 of report."""
    perm = list([57,49,41,33,25,17,9,
                1,58,50,42,34,26,18,
                10,2,59,51,43,35,27,
                19,11,3,60,52,44,36,
                63,55,47,39,31,23,15,
                7,62,54,46,38,30,22,
                14,6,61,53,45,37,29,
                21,13,5,28,20,12,4])

    cKey = list([0]*28)
    dKey = list([0]*28)

    for i in range(0,28):
        cKey[i] = key[perm[i]-1]

    for i in range(28,56):
        dKey[i-28] = key[perm[i]-1]

    return cKey, dKey


def pc2(key):
    '''
    Permuted Choice 2 - 56 bit key is permuted and reduced to 48 bit
    Permutation boxes is discussed on Page 3 of report.
    '''
    perm = list([14,17,11,24,1,5
                ,3,28,15,6,21,10,
                23,19,12,4,26,8,
                16,7,27,20,13,2,
                41,52,31,37,47,55,
                30,40,51,45,33,48,
                44,49,39,56,34,53,
                46,42,50,36,29,32])

    newKey = list([0]*48)

    for i in range(0,48):
        newKey[i] = key[perm[i]-1]

    return newKey


def ipbox(key):
    """
    Initial permutation. Takes 64 bit key as input which is permuted using the permution table.
    Permutation boxes is discussed on Page 3 of report.
    """
    perm = list([58,50,42,34,26,18,10,2,
                 60,52,44,36,28,20,12,4,
                 62,54,46,38,30,22,14,6,
                 64,56,48,40,32,24,16,8,
                 57,49,41,33,25,17,9,1,
                 59,51,43,35,27,19,11,3,
                 61,53,45,37,29,21,13,5,
                 63,55,47,39,31,23,15,7])

    newKey = list([0]*len(key))

    for i in range(0,len(key)):
        newKey[i] = key[perm[i]-1]

    return newKey


def invipbox(key):
    """ Inverse initial permutation. Permutation boxes is discussed on Page 3 of report."""
    invperm = list([40,     8,   48,    16,    56,   24,    64,   32,
                    39,     7,   47,    15,    55,   23,    63,   31,
                    38,     6,   46,    14,    54,   22,    62,   30,
                    37,     5,   45,    13,    53,   21,    61,   29,
                    36,     4,   44,    12,    52,   20,    60,   28,
                    35,     3,   43,    11,    51,   19,    59,   27,
                    34,     2,   42,    10,    50,   18,    58,   26,
                    33,     1,   41,     9,    49,   17,    57,   25])

    newKey = list([0]*64)

    for i in range(0,64):
        newKey[i] = key[invperm[i]-1]

    return newKey


def expansion(key):
    """
    Expanding key from 32 to 48 bit
    Discussed on page 4 of the report.
    """
    size = 4
    newBlcoks = []
    firstBlock = []
    firstBlock.append(key[-1])
    for i in range(0,5):
        firstBlock.append(key[i])

    newBlcoks.append(firstBlock)

    startvalue1 = -1
    startvalue2 = 5

    for i in range(1,8):
        block = key[startvalue1+(i*size):startvalue2+(i*size)]
        if i == 7:
            block.append(key[0])

        newBlcoks.append(block)
    result = []
    for block in newBlcoks:
        for bit in block:
            result.append(bit)



    return result


def sbox(key):
    '''
    Substitution box: Takes a key, splits it, and substitutes each part against the declared s-boxes.
    Discussed on Page 3 of report.
    '''

    s1 = list([14,4,13,1,2,15,11,8,3,10,6,12,5,9,0,7,
                0,15,7,4,14,2,13,1,10,6,12,11,9,5,3,8,
                4,1,14,8,13,6,2,11,15,12,9,7,3,10,5,0,
                15,12,8,2,4,9,1,7,5,11,3,14,10,0,6,13
                ])

    s2 = list([15,1,8,14,6,11,3,4,9,7,2,13,12,0,5,10,
                3,13,4,7,15,2,8,14,12,0,1,10,6,9,11,5,
                0,14,7,11,10,4,13,1,5,8,12,6,9,3,2,15,
                13,8,10,1,3,15,4,2,11,6,7,12,0,5,14,9
                ])

    s3 = list([10,0,9,14,6,3,15,5,1,13,12,7,11,4,2,8,
                13,7,0,9,3,4,6,10,2,8,5,14,12,11,15,1,
                13,6,4,9,8,15,3,0,11,1,2,12,5,10,14,7,
                1,10,13,0,6,9,8,7,4,15,14,3,11,5,2,12
                ])

    s4 = list([7,13,14,3,0,6,9,10,1,2,8,5,11,12,4,15,
                13,8,11,5,6,15,0,3,4,7,2,12,1,10,14,9,
                10,6,9,0,12,11,7,13,15,1,3,14,5,2,8,4,
                3,15,0,6,10,1,13,8,9,4,5,11,12,7,2,14
                ])

    s5 = list([2,12,4,1,7,10,11,6,8,5,3,15,13,0,14,9,
                14,11,2,12,4,7,13,1,5,0,15,10,3,9,8,6,
                4,2,1,11,10,13,7,8,15,9,12,5,6,3,0,14,
                11,8,12,7,1,14,2,13,6,15,0,9,10,4,5,3
                ])

    s6 = list([12,1,10,15,9,2,6,8,0,13,3,4,14,7,5,11,
                10,15,4,2,7,12,9,5,6,1,13,14,0,11,3,8,
                9,14,15,5,2,8,12,3,7,0,4,10,1,13,11,6,
                4,3,2,12,9,5,15,10,11,14,1,7,6,0,8,13
                ])

    s7 = list([4,11,2,14,15,0,8,13,3,12,9,7,5,10,6,1,
                13,0,11,7,4,9,1,10,14,3,5,12,2,15,8,6,
                1,4,11,13,12,3,7,14,10,15,6,8,0,5,9,2,
                6,11,13,8,1,4,10,7,9,5,0,15,14,2,3,12
                ])

    s8 = list([13,2,8,4,6,15,11,1,10,9,3,14,5,0,12,7,
                1,15,13,8,10,3,7,4,12,5,6,11,0,14,9,2,
                7,11,4,1,9,12,14,2,0,6,10,13,15,3,5,8,
                2,1,14,7,4,10,8,13,15,12,9,0,3,5,6,11])

    rowkvp = {'00':1, '01':2, '10':3, '11':4}
    colkvp = {'0000':0, '0001':1, '0010':2, '0011':3, '0100':4,
              '0101':5, '0110':6, '0111':7, '1000':8, '1001':9,
              '1010':10,'1011':11,'1100':12,'1101':13,'1110':14,'1111':15}

    sboxlist = []
    sboxlist.append(s1); sboxlist.append(s2); sboxlist.append(s3); sboxlist.append(s4)
    sboxlist.append(s5); sboxlist.append(s6); sboxlist.append(s7); sboxlist.append(s8)

    keyStr = ''.join(str(val) for val in key)
    blocksize = 6
    blocks = []

    for b in range((len(keyStr) // blocksize)):  # // forces integer division
        blocks.append(keyStr[b*blocksize:(b+1)*blocksize])

    newKey = []

    for i in range(0,len(blocks)):

        fst = blocks[i][0]; snd = blocks[i][1]; trd = blocks[i][2]
        fth = blocks[i][3]; fih = blocks[i][4]; six = blocks[i][5]

        rowvalue = fst+six
        colvalue = snd+trd+fth+fih

        chosenrow = rowkvp[rowvalue]
        chosencol = colkvp[colvalue]

        # sbox number i, and column*row value because it is a list and not a matrix
        outputVal = sboxlist[i][chosencol*chosenrow]

        outputVal = format(outputVal, '04b')
        outputVal = list(outputVal)

        for bit in outputVal:
            newKey.append(int(bit))

    return newKey


def prng(seed, rounds):
    """
    Pseudo Random Number Generator which uses the Linear congruential generator
    Creating a list of pseudo random keys, one for each round in roundfunction()
    Discussed on Page 3 of report.
    """

    X_n = seed
    m = 2**32
    c = 1013904223
    a = 1664525

    randkeys = []

    for i in range(0,rounds+1):
        X_n_1 = (a*X_n + c) % m
        X_n = X_n_1
        randkeys.append(X_n_1)


    result = []

    for key in randkeys:
        newkey = tobits(str(key))
        result.append(newkey)

    return result


def subkeyGenerator(key, seed, rounds):
    """
    Generates subkeys from input key and seed from user.
    Discussed on Page 2 of report.
    """
    prn = prng(seed, rounds)  # generate pseudo random numbers

    ckey, dkey = pc1(key)  # 64 to 56 (28 + 28)
    cRotate = deque(ckey)
    dRotate = deque(dkey)

    keyVal = {1:1, 2:1, 3:2, 4:2, 5:2, 6:2, 7:2, 8:2, 9:1, 10:2, 11:2, 12:2, 13:2, 14:2, 15:2, 16:1,
              32:1, 31:1, 30:2, 29:2, 28:2, 27:2, 26:2, 25:2, 24:1, 23:2, 22:2, 21:2, 20:2, 19:2, 18:2, 17:1}

    subkeyList = []

    for i in range(1,rounds+1):

        cRotate.rotate(keyVal[i])  # left shift key
        dRotate.rotate(keyVal[i])

        ckey = list(cRotate)
        dkey = list(dRotate)

        key = ckey           # 28 bit
        for bit in dkey:
            key.append(bit)  # 56 bit

        key = pc2(key)       # 48 bit

        key = xor(key, prn[i])  # xor the key with a prn
        subkeyList.append(key)  # add the sub key to the list of sub keys

    return subkeyList


# if key is less than 64 bits, the xor is performed 0 - n and then starts from 0 again. Secure?
def xor(block, key):
    """ Perform an XOR operation with a block and a key """
    count = 0
    msg = []
    keysize = len(key)

    for bit in block:
        result_bit = bit ^ key[count]
        count += 1

        if count == keysize:
            count = 0

        msg.append(result_bit)

    return msg


def split_block(block):
    """ Splits a block in two halves which is returned """
    half = len(block)//2
    return block[:half], block[half:]


def function(block, subkey):
    """
    Block is first expanded from 32 to 48 bit.
    Block is XOR'ed with a subkey
    Block is substituted and reduced from 48 to 32 bit.
    Block is permuted in a permutation box
    """
    block = expansion(block)  # 32 to 48
    block = xor(block, subkey)  # 48
    block = sbox(block)  # 48 to 32
    block = p32box(block)

    return block


def roundfunction(block, subkey):
    """ Roundfunction. See report for explanation """

    lBlock, rBlock = split_block(block)
    newLblock = rBlock
    rBlock = function(rBlock, subkey)
    rBlock = xor(lBlock, rBlock)
    lBlock = newLblock

    result = lBlock

    for c in rBlock:
        result.append(c)

    return result


def encryption(string, blocksize, rounds, subkeys):
    """ Master method for encryption. Discussed on Page 3 of report."""
    block = tobits(string)

    bitblocks = []

    for b in range((len(block) // blocksize)):  # split blocks into block size
        bitblocks.append(block[b*blocksize:(b+1)*blocksize])

    # if the plaintext input from user is not modulo blocksize,
    # then the last block is less than blocksize and needs padding.
    if (len(block) % 64) != 0:

        length = len(block)  # get length of block
        lastblock = block[(b+1)*blocksize:length]  # create block of the actual size (less than block size)
        createlast = []
        emptyblock = [0]*(blocksize - len(lastblock))  # create block of leading zeroes

        for i in emptyblock:
            createlast.append(emptyblock[i])
        for bit in lastblock:
            createlast.append(bit)  # add the bits of the last block to the block with leading zeroes

        bitblocks.append(createlast)


    encryptedBlocks = []

    for block in bitblocks:
        block = ipbox(block)  # initial permutation of the block

        for i in range(0, rounds):
            block = roundfunction(block, subkeys[i])  # round function on each block

        block = swap_block_halves(block)  # final swap of block halves
        encryptedBlocks.append(block)  # append blocks to the result

    return encryptedBlocks


def decryption(encryptedbits, blocksize, rounds, subkeys):
    """ Master method for decryption. Discussed on Page 3 of report."""
    decryptedBlocks = []
    encryptedBlocks = []

    for b in range((len(encryptedbits) // blocksize)):  #  create blocks of the input
        encryptedBlocks.append(encryptedbits[b*blocksize:(b+1)*blocksize])

    for block in encryptedBlocks:   # run the round  function with sub keys in opposite order
        for i in range(rounds-1,-1,-1):
            block = roundfunction(block, subkeys[i])

        block = swap_block_halves(block)
        newblock = invipbox(block)  # inverse initial permutation

        decryptedBlocks.append(newblock)

        lastblock = newblock

    # if there are more than one block, we need to operate on the last block to see if it
    # really is characters or filled with padded 0's
    if len(decryptedBlocks) > 1:
        decryptedBlocks.pop(-1)  # remove the last block, which is full of zeroes.
        popblock = list(lastblock)

        for bit in lastblock:
            if bit == 1:
                break

            lastblock = list(popblock)
            popblock.pop(bit)

        decryptedBlocks.append(lastblock)

    decryptedArray = []

    for block in decryptedBlocks:
        for bit in block:
            decryptedArray.append(bit)

    return decryptedArray


def swap_block_halves(block):
    """Takes in one block and swaps the two halves."""

    lblock, rblock = split_block(block)

    newLBlock = lblock
    lblock = rblock
    rblock = newLBlock

    result = lblock
    for bit in rblock:
        result.append(bit)

    return result


# On startup, the following code is called.
main()