-module(count).
-export([to_ten/0]).

to_ten() -> to_ten({10, 0}).

to_ten({0, N}) -> N;
to_ten({Step, N}) -> to_ten({Step - 1, N + 1}). 
