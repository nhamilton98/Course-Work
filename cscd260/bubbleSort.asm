.data 
	array: .space 40
	space:  .asciiz " "
	newline: .asciiz "\n"
	prompt: .asciiz "Input 10 Integers:\n------------------\n"
	inputArray: .asciiz "Array: "
	sortedArray: .asciiz "Sorted: "

.text
	main:
		# Prompt the user
		li $v0, 4
		la $a0, prompt
		syscall

		addi $t0, $0, 0	# Set loop value
   	
		# Input loop
		inputLoop:
			beq $t0, 40, endInput	# Exit condition

			# Take value from stdin
			li $v0, 5
			syscall

			sw $v0, array($t0)	# Store value in next available array location
			add $t0, $t0, 4		# Increment to next location

			j inputLoop	# Continue loop
		endInput:	

		addi $t0, $0, 0	# Reset loop value t0

		# Print inputArray
		li $v0, 4
		la $a0, inputArray
		syscall

		# Print array
		printArray:
			beq $t0, 40, exitPrint	# Exit condition

			lw $t3, array($t0)	# Load next array address to t3
			addi $t0, $t0, 4	# Increment loop value

			# Print value
			li $v0, 1
			move $a0, $t3
			syscall

			# Print space
			li $v0, 4
			la $a0, space
			syscall

			j printArray	# Continue loop
		exitPrint:
			li $v0, 4
			la $a0, newline
			syscall
   		
		la  $t0, array	# Load array starting address to t0
		add $t0, $t0, 40	# Loop value 
	    
		# Outter bubble sort loop
		outterLoop:
			add $t1, $0, $0     # Use t1 as flag for when the sort is complete
			la  $a0, array      # Load array starting address to a0

		# Inner bubble sort loop
		innerLoop:
    		lw  $t2, 0($a0)         # Load current value into t2
    		lw  $t3, 4($a0)         # Load next value into t3

			slt $t4, $t3, $t2       # Set t4 = 1 if t2 < t3
			beq $t4, $0, continue   # Skip swap if t4 is 0

			# Swap values
			add $t1, $0, 1
			sw  $t2, 4($a0)
			sw  $t3, 0($a0)
		continue:
    		addi $a0, $a0, 4	# Increment array address
			bne  $a0, $t0, innerLoop    # Continue innerLoop if not at the end of the array
			bne  $t1, $0, outterLoop    # Continue outterLoop if at the end of the array

		# Print sortedArray
		li $v0, 4
		la $a0, sortedArray
		syscall

  		addi $t0, $0, 0	# Reset loop value t0

		# Print sorted array
		printSorted:
			beq $t0, 40, endPrintSorted	# Exit condition

			lw $t3, array($t0)	# Load next array address to t3
			addi $t0, $t0, 4	# Increment loop value

			# Print value
			li $v0, 1
			move $a0, $t3
			syscall

			# Print space
			li $v0, 4
			la $a0, space
			syscall

			j printSorted	# Continue loop
		endPrintSorted: 
   			li $v0, 10
			syscall