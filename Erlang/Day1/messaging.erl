-module(messaging).
-export([print_message/1]).

print_message(success) -> "success";
print_message({error, Message}) -> string:concat("error: ",  Message).
