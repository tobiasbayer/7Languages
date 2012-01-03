% Usage: 
% T = [{erlang, "a functional language"}, {ruby, "an OO language"}].
% key_value:value_for_key(ruby, T).
% --> "an OO language"

-module(key_value).
-export([value_for_key/2]).

% 1. Extract the matching tuple
% 2. Take the last element from the list (there's only one)
% 3. Return the second element (the value) from the tuple
value_for_key(Key, Tuples) -> 
  element(2, lists:last(extract_tuple(Key, Tuples))).

% Reduce the list to the tuple with the matching key
extract_tuple(Key, Tuples) -> lists:filter(fun({K, _}) -> K == Key end, Tuples).
