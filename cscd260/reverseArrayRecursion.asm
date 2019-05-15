.data
    array1: .word 0, 1, 2, 3, 4, 5, 6, 7, 8, 9
    array2: .word 0, 2, 4, 6, 8, 10, 12, 14, 16, 18
    array: .asciiz "Array:                  "
    reversed1: .asciiz "Reversed Indeces 0-9:   "
    reversed2: .asciiz "Reversed Indeces 2-8:   "
    newline: .asciiz "\n"
    space: .asciiz " "

.text
    main:
        # Store original return address
        addi $sp, $sp, -4
        sw $ra, 0($sp)

        # Print array string
        li $v0, 4
        la $a0, array
        syscall

        # Print original array1
        addi $sp, $sp, -4
        la $t0, array1   # &array1[0]
        move $a1, $t0
        sw $a1, 0($sp)  # Store argument on stack

        jal printArray  # Call printArray subroutine

        addi $sp, $sp, 4    # Clear argument

        # Decrement stack to make room for arguments
        addi $sp, $sp, -8

        # Store arguments &array1[0] and &array1[9] on stack
        la $t0, array1   # &array1[0]
        move $a1, $t0
        sw $a1, 0($sp)

        addi $t0, $t0, 36   # &array1[9]
        move $a2, $t0
        sw $a2, 4($sp)

        jal reverse # Call reverse subroutine

        addi $sp, $sp, 8    # Clear original arguments

        # Print reversed string
        li $v0, 4
        la $a0, reversed1
        syscall

        # Print reversed array1
        addi $sp, $sp, -4
        la $t0, array1   # &array1[0]
        move $a1, $t0
        sw $a1, 0($sp)  # Store argument on stack

        jal printArray  # Call printArray subroutine

        addi $sp, $sp, 4    # Clear argument

        # Print newline string
        li $v0, 4
        la $a0, newline
        syscall

        # Print array string
        li $v0, 4
        la $a0, array
        syscall

        # Print original array2
        addi $sp, $sp, -4
        la $t0, array2   # &array2[0]
        move $a1, $t0
        sw $a1, 0($sp)  # Store argument on stack

        jal printArray  # Call printArray subroutine

        addi $sp, $sp, 4    # Clear argument

        # Decrement stack to make room for arguments
        addi $sp, $sp, -8

        # Store arguments &array2[2] and &array2[8] on stack
        la $t0, array2
        addi $t0, $t0, 8    # &array2[2]
        move $a1, $t0
        sw $a1, 0($sp)

        addi $t0, $t0, 24   # &array2[8]
        move $a2, $t0
        sw $a2, 4($sp)

        jal reverse # Call reverse subroutine

        addi $sp, $sp, 8    # Clear original arguments

        # Print reversed string
        li $v0, 4
        la $a0, reversed2
        syscall

        # Print reversed array1
        addi $sp, $sp, -4
        la $t0, array2   # &array2[0]
        move $a1, $t0
        sw $a1, 0($sp)  # Store argument on stack

        jal printArray  # Call printArray subroutine

        addi $sp, $sp, 4    # Clear argument

        # Restore original return address
        lw $ra, 0($sp)
        addi $sp, $sp, 4

        li $v0, 10
        syscall

reverse:
    # Retrieve parameters
    lw $t0, 4($sp)  # Right side address
    lw $t1, 0($sp)  # Left side address

    ble $t0, $t1, endReverse    # Base case - if right address is less than or equal to left address

    # Retrieve values at parameter memory addresses
    lw $t2, ($t1)   # Left side value
    lw $t3, ($t0)   # Right side value

    # Swap values
    sw $t2, 0($t0)
    sw $t3, 0($t1)

    # Increment addresses
    addi $t0, $t0, -4
    addi $t1, $t1, 4

    # Store current return address
    addi $sp, $sp, -4
    sw $ra, 0($sp)

    # Store arguments and call reverse subroutine
    addi $sp, $sp, -8
    sw $t0, 4($sp)
    sw $t1, 0($sp)

    jal reverse # Call reverse subroutine

    addi $sp, $sp, 8    # Clear parameters of current recursive call

    # Retrieve return address of current recursive call and clear stack space
    lw $ra, 0($sp)
    addi $sp, $sp, 4

    endReverse:
        jr $ra  # Return to last call

printArray:
    lw $t0, 0($sp)  # Retrieve argument
    
    # Store return address
    addi $sp, $sp, -4
    sw $ra, 0($sp)

    addi $t1, $0, 0 # Loop value

    printLoop:
        beq $t1, 10, endPrint   # Exit condition

        # Print current value
        lw $t2, 0($t0)
        li $v0, 1
        move $a0, $t2
        syscall

        # Print space string
        li $v0, 4
        la $a0, space
        syscall

        # Increment array address and loop value
        addi $t0, $t0, 4
        addi $t1, $t1, 1

        j printLoop # Continue loop
    
    endPrint:
        # Print newline string
        li $v0, 4
        la $a0, newline
        syscall

        # Restore return address and clear stack allocation
        lw $ra, 0($sp)
        addi $sp, $sp, 4

        jr $ra  # Return