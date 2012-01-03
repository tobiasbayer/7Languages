x = rand(10)
ok = false
while not ok do
  puts 'Your guess: '
  guess = gets
  puts 'Too high' if guess.to_i > x
  puts 'Too low' if guess.to_i < x
  ok = (guess.to_i == x)
end

puts 'Your guess was right!'
