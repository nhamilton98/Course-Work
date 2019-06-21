.data
    tid: .word 0
    tcb0: .space 128    # task0 control block
    tcb1: .space 128    # task1 control block

    str0:   .asciiz "123"
    str1:   .asciiz "456"

.text
    main:
        # Store addresses of task0 and task1 into corresponding tcb
        la $a0, task0
        la $s0, tcb0
        sw $a0, 124($s0)
        la $a1, task1
        la $s1, tcb1
        sw $a1, 124($s1)

        # Set tid to 0
        la $t1, tid
        addi $t2, $0, 0
        sw $t2, 0($t1)
        
        # Provided task0
        task0:
            add  $t0, $0, $0
            jal ts     
            addi $t1, $0, 10     
            la   $s0, str0       
            jal ts
            beg0:
                lb   $t2, ($s0)     
                beq  $t2, $0, quit0 
                sub  $t2, $t2, '0'  
                mult $t0, $t1       
                mflo $t0
                add  $t0, $t0, $t2  
                jal ts
                add  $s0, $s0, 1    
                b    beg0
            quit0:
                jal ts
                add  $v1, $0, $t0
                add  $s0, $0, $v1
                add  $a1, $0, $s0 
                jal ts
                add  $t5, $0, $a1
                add  $t6, $0, $t5
                addi $s0, $0, 1
                add  $v0, $0, $s0
                add  $a0, $0, $t6
                jal ts
                syscall
                j task0 

        # Provided task1
        task1:
            add  $t0, $0, $0    
            addi $t1, $0, 10    
            la   $s0, str1      
            beg1:
                lb   $t2, ($s0)      
                beq  $t2, $0, quit1 
                jal ts
                sub  $t2, $t2, '0'  
                mult $t0, $t1       
                mflo $t0
                add  $t0, $t0, $t2   
                add  $s0, $s0, 1     
                b    beg1
            quit1:
                add  $v1, $0, $t0
                add  $s0, $0, $v1
                jal ts
                add  $a1, $0, $s0 
                add  $t5, $0, $a1
                jal ts
                add  $t6, $0, $t5
                jal ts
                addi $s0, $0, 1
                add  $v0, $0, $s0
                jal ts
                add  $a0, $0, $t6
                jal ts
                syscall
                j task1 

    # Task Switch
    ts:
        # Store a0 on stack and load tid into a0
        addi $sp, $sp, -4
        sw $a0, 0($sp)
        la $a0, tid
        lw $a0, 0($a0)

        # Go to label corresponding with current tid
        beq $a0, 0, t0
        beq $a0, 1, t1
    
        t0:
            # Set a0 to address of tcb0
            la $a0, tcb0

            # Store all registers into tcb
            sw $v0, 0($a0)
            sw $v1, 4($a0)
            sw $a1, 12($a0)
            sw $a2, 16($a0)
            sw $a3, 20($a0)
            sw $t0, 24($a0)
            sw $t1, 28($a0)
            sw $t2, 32($a0)
            sw $t3, 36($a0)
            sw $t4, 40($a0)
            sw $t5, 44($a0)
            sw $t6, 48($a0)
            sw $t7, 52($a0)
            sw $s0, 56($a0)
            sw $s1, 60($a0)
            sw $s2, 64($a0)
            sw $s3, 68($a0)
            sw $s4, 72($a0)
            sw $s5, 76($a0)
            sw $s6, 80($a0)
            sw $s7, 84($a0)
            sw $t8, 88($a0)
            sw $t9, 92($a0)
            sw $k0, 96($a0)
            sw $k1, 100($a0)
            sw $gp, 104($a0)
            sw $fp, 108($a0)
            sw $ra, 124($a0)

            # Load a0 from stack and store into tcb
            lw $t0, 0($sp)
            sw $t0, 8($a0)
            addi $sp, $sp, 4
            
            # Change tid
            la $t1, tid
            addi $t2, $0, 1
            sw $t2, 0($t1)
            
            # Set a0 to address of tcb1
            la $a0, tcb1

            # Load all registers from tcb
            lw $v0, 0($a0)
            lw $v1, 4($a0)
            lw $a1, 12($a0)
            lw $a2, 16($a0)
            lw $a3, 20($a0)
            lw $t0, 24($a0)
            lw $t1, 28($a0)
            lw $t2, 32($a0)
            lw $t3, 36($a0)
            lw $t4, 40($a0)
            lw $t5, 44($a0)
            lw $t6, 48($a0)
            lw $t7, 52($a0)
            lw $s0, 56($a0)
            lw $s1, 60($a0)
            lw $s2, 64($a0)
            lw $s3, 68($a0)
            lw $s4, 72($a0)
            lw $s5, 76($a0)
            lw $s6, 80($a0)
            lw $s7, 84($a0)
            lw $t8, 88($a0)
            lw $t9, 92($a0)
            lw $k0, 96($a0)
            lw $k1, 100($a0)
            lw $gp, 104($a0)
            lw $fp, 108($a0)
            lw $ra, 124($a0)
            lw $a0, 8($a0)

            jr $ra  # Return
    
        t1:
            # Set a0 to address of tcb1
            la $a0, tcb1

            # Load all registers into tcb
            sw $v0, 0($a0)
            sw $v1, 4($a0)
            sw $a1, 12($a0)
            sw $a2, 16($a0)
            sw $a3, 20($a0)
            sw $t0, 24($a0)
            sw $t1, 28($a0)
            sw $t2, 32($a0)
            sw $t3, 36($a0)
            sw $t4, 40($a0)
            sw $t5, 44($a0)
            sw $t6, 48($a0)
            sw $t7, 52($a0)
            sw $s0, 56($a0)
            sw $s1, 60($a0)
            sw $s2, 64($a0)
            sw $s3, 68($a0)
            sw $s4, 72($a0)
            sw $s5, 76($a0)
            sw $s6, 80($a0)
            sw $s7, 84($a0)
            sw $t8, 88($a0)
            sw $t9, 92($a0)
            sw $k0, 96($a0)
            sw $k1, 100($a0)
            sw $gp, 104($a0)
            sw $fp, 108($a0)
            sw $ra, 124($a0)

            # Load a0 from stack and store into tcb
            lw $t0, 0($sp)
            sw $t0, 8($a0)
            addi $sp, $sp, 4
            
            # Change tid
            la $t1, tid
            addi $t2, $0, 0
            sw $t2, 0($t1)
            
            # Set a0 to address of tcb0
            la $a0, tcb0

            # Load all registers from tcb
            lw $v0, 0($a0)
            lw $v1, 4($a0)
            lw $a1, 12($a0)
            lw $a2, 16($a0)
            lw $a3, 20($a0)
            lw $t0, 24($a0)
            lw $t1, 28($a0)
            lw $t2, 32($a0)
            lw $t3, 36($a0)
            lw $t4, 40($a0)
            lw $t5, 44($a0)
            lw $t6, 48($a0)
            lw $t7, 52($a0)
            lw $s0, 56($a0)
            lw $s1, 60($a0)
            lw $s2, 64($a0)
            lw $s3, 68($a0)
            lw $s4, 72($a0)
            lw $s5, 76($a0)
            lw $s6, 80($a0)
            lw $s7, 84($a0)
            lw $t8, 88($a0)
            lw $t9, 92($a0)
            lw $k0, 96($a0)
            lw $k1, 100($a0)
            lw $gp, 104($a0)
            lw $fp, 108($a0)
            lw $ra, 124($a0)
            lw $a0, 8($a0)

            jr $ra  # Return