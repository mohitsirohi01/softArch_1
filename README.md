# softArch_1

How to run?

1 git pull
2 compile Blackboard, ClientAggr, ClientParital
3 run Blackboard: java Blackboard port_par port_agg : Give port number to which ClientParial and ClientAgg will connect
4 run ClientPartial IP/localhost port_part : It will get the content of myText1.txt file
5 run ClientAggr IP/localhost port_agg : It will get the content of myText2.txt file


6 OverView: Server creates two threads to listen on two diffrent ports provied during runtime. ClientPartial send KS1(Knowledge Source1) and ClientAgg send KS2(Knowledge Source2) as part of request. Server checks the request if it begins with KS1 it returns the content of myText1.txt file. If it begins with KS2 it returns the content of myText2.txt file.
