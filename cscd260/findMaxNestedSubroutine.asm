.data
	# Global variables
	space: .asciiz " "
	newLine: .asciiz "\n"
	prompt: .asciiz "Input 10 Integers:\n------------------\n"
	maxNum: .asciiz "Max: "

.text
	main:
		# Store original return address
		addi $sp, $sp, -4
		sw $ra, 0($sp)

		# Prompt the user
		li $v0, 4
		la $a0, prompt
		syscall

		addi $sp, $sp, -40 # Create room for array on stack

		# Take 10 values from stdin
		li $v0, 5
		syscall
		sw $v0, 0($sp)

		li $v0, 5
		syscall
		sw $v0, 4($sp)

		li $v0, 5
		syscall
		sw $v0, 8($sp)

		li $v0, 5
		syscall
		sw $v0, 12($sp)

		li $v0, 5
		syscall
		sw $v0, 16($sp)

		li $v0, 5
		syscall
		sw $v0, 20($sp)

		li $v0, 5
		syscall
		sw $v0, 24($sp)

		li $v0, 5
		syscall
		sw $v0, 28($sp)

		li $v0, 5
		syscall
		sw $v0, 32($sp)

		li $v0, 5
		syscall
		sw $v0, 36($sp)

		# Load array starting address and size to stack
		la $a1, ($sp)
		addi $sp, $sp, -8
		addi $a2, $0, 10
		sw $a2, 4($sp)
		sw $a1, 0($sp)

		# Call findMax subroutine
		jal findMax

		move $t2, $v0	# Store returned max value in a0

		# Print newLine label
		li $v0, 4
		la $a0, newLine
		syscall

		# Print maxNum label
		li $v0, 4
		la $a0, maxNum
		syscall

		# Print max value
		li $v0, 1
		move $a0, $t2
		syscall

		lw $fp, 0($sp)	# Restore frame pointer's original data

		addi $sp, $sp, 48	# Clear stack data up to original return address

		la $ra, 0($sp)	# Restore original return address
		addi $sp, $sp, 4	# Clear stack
		
		# Exit program
		li $v0, 10
		syscall

# findMax subroutine
findMax:
	# Store frame pointer's current data
	addi $sp, $sp, -4
	sw $fp, 0($sp)

	move $fp, $sp	# Set frame pointer to stack pointer

	lw $t0, 4($fp)	# Retrieve starting address from stack, relative to frame pointer
	lw $t1, 8($fp)	# Retrieve size from stack, relative to frame pointer

	lw $t2, ($t0)	# Use t2 for temporary max value

	addi $t0, $t0, 4	# Move to next array value
	addi $t3, $0, 1		# Set loop value

	maxLoop:
		beq $t3, $t1, endMax	# Exit condition

		# Check if current value is larger than temporary max value
		lw $t4, ($t0)
		bgt $t4, $t2, setMax

		# Increment and continue loop
		addi $t0, $t0, 4
		addi $t3, $t3, 1
		j maxLoop
	setMax:
		move $t2, $t4	# Store new max

		# Increment and continue loop
		addi $t0, $t0, 4
		addi $t3, $t3, 1
		j maxLoop
	endMax:
		# Load return address to stack
		addi $sp, $sp, -4
		sw $ra, 0($sp)

		# Store max value on stack
		move $a3, $t2
		addi $sp, $sp, -4
		sw $a3, 0($sp)

		jal printNum	# Call printNum subroutine

		lw $fp, 0($sp)	# Restore frame pointer's original data
		addi $sp, $sp, 4	# Clear frame pointer stack allocation

		lw $ra, 4($sp)	# Retrieve stored return address

		addi $sp, $sp, 8	# Clear stack allocation made within findMax

		move $v0, $a3	# Return max value

		jr $ra	# Return to main

printNum:
	# Store the frame pointer's current data
	addi $sp, $sp, -4
	sw $fp, 0($sp)

	move $fp, $sp	# Set frame pointer to stack pointer

	lw $t2, 4($fp)	# Retrieve max value from stack, relative to frame pointer

	# Print maxNum label
	li $v0, 4
	la $a0, maxNum
	syscall

	# Print max value
	li $v0, 1
	move $a0, $t2
	syscall

	jr $ra	# Return to findMax