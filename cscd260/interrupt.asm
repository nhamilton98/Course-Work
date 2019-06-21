.data
    ch: .byte 'a'
.kdata

# Interrupt
.ktext 0x80000180
    # Read from keyboard
    lui $t0, 0xffff
    lw $k1, 4($t0)

    # Load entered character into k1
    la $k0, ch
    sw $k1, 0($k0)

    mtc0 $0, $13    # Set $13 to 0 to signify interrupt
    mfc0 $k0, $12   # Retrieve value in $12
    ori $k0, 0x1    # Or $12 with 0x1 to allow for interrupts
    mtc0 $k0, $12   # Set $12 to result of previous line
    eret
.text
.globl _start
.globl main
    # Prepare keyboard for use
    _start:
        lui $s0, 0xFFFF
        lw $t6, 4($s0)
        li $t0, 0x02
        sw $t0, 0($s0)
        mfc0 $t0, $12
        ori $t0, $t0, 0xFF01
        mtc0 $t0, $12
        jal main

        main:
            # Print current character
            li $v0, 11
            lw $a0, ch
            syscall
            continue:
                # Check if keyboard is ready to be read
                lui $t1, 0xFFFF
                lw $t2, 8($t1)
                andi $t2, $t2, 1
                beq $t2, $0, continue

                # Load character stored in ch address
                la $t3, ch
                lw $t3, 0($t3)
                sw $t3, 12($t1)

                j main  # Jump to start of loop