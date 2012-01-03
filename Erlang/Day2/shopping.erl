-module(shopping).
-export([totals/1]).

totals(ShoppingList) -> [{Item, Price * Quantity} || {Item, Quantity, Price} <- ShoppingList].
