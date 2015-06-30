# r-client
Basic R Client for Java

A Simple Client on top of RServe (http://rforge.net/Rserve/doc.html).
Currently manages connections including pooling, closing etc
Basic handling for exceptions
Reduces redundant code for execution

[WORK IN PROGRESS]
TODO:
- Logging
- Command processor for passing and setting up arguments
- Custom exceptions and exception translation
- Allow R source load during initialization : Although it is recommended that the source
  to be loaded through Rserve config during initialization, this option needs to be
  supported for simple usage/testing
- Tests

Dependencies:
-  RServe - http://rforge.net/Rserve/doc.html
-  Apache commons pool
-  slf4j for logging - can be removed if not needed
-  Java 1.8 ( 1.7+ ?)

Usage:
-  Extend your client from BaseRClient
-  Pass your implementation to handleExecution() call, RConnection would be passed as parameter
        
        public double someRFunction([Params ...]) {

           return handleExecution(rConnection ->{
               //Your variable assignments and other code to be executed here...
               rConnection.assign("var1", new REXPInteger(requiredWinsForSeries));
               rConnection.assign("var2", new REXPInteger(numGamesInSeries));
               rConnection.assign("var3", new REXPInteger(teamNumWins));
               rConnection.assign("var4", new REXPInteger(teamNumLosses));

               return rConnection.parseAndEval("someRFunction(var1, var2, var3, var4)").asDouble();
           });
        }

License:

    TODO
