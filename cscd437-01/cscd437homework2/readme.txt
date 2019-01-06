404 Group Not Found

Nathan Hamilton
Matthew Hopkins
Forrest Wallace


Shortcomings:
We programmed our solution in Java. Due to the nature of Java being that the class name must match the filename, running a diff (fc in Windows)
produces a long list of inconsistencies between the two files. In actuality, the only difference is the class name is changed from Q to Q$.
Changing the filename to Q$.java (and subsequently the class name to Q$) is needed so that we have two seperate files to run a diff on. If you
compare character for character the two files, you will also find that the only difference is the class name.

We attempted the extra credit but were unable to complete it.