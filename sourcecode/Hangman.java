import java.util.Random;
import java.util.Scanner;

public class Hangman {


	private String word;
	private String guessWord;
	private String initString = "----------";
	private String wrongGuess;
	private int guessCounter = 8;
	private boolean gameOver;
	
	private HangmanLexicon lex = new HangmanLexicon();
	
	Scanner in = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
		
		Hangman hangman = new Hangman();
		hangman.newGame();
	}

	private void newGame() throws Exception{
		Random rn = new Random();
		int wordIndex = rn.nextInt(lex.getWordCount());
		word = lex.getWord(wordIndex);
		guessWord = initString.substring(0, word.length());
		System.out.println("Welcome to Hangman!");
//		System.out.println(guessWord);
		playGame();
	}
	
	private void playGame(){
		char guess;
		while(!isGameOver()){
			System.out.println("The word looks like this: " + guessWord);
			System.out.printf("You have %d guesses left.\n", guessCounter);
			
			guess = getInputandValidate();
			checkGuess(guess);
		}
			
	}

	private boolean isGameOver(){
		if (guessCounter==0){
			System.out.println("You're completely hung.");
			System.out.println("The word was: " + word);
			System.out.println("You lose.");
			return true;
		} else if(guessWord.equals(word)) {
			System.out.println("You guessed the word: " + word);
			System.out.println("You win.");
			return true;
		}
		return false;

	}
	private boolean checkGuess(char guess){
		if (word.indexOf(guess)==-1){
			guessCounter--;
			System.out.printf("There is no %c's in the word.\n", guess);
			wrongGuess += guess;
		} else {
			System.out.println("That guess is correct.");
			for(int i=0; i<word.length();i++){
				if(word.charAt(i)==guess && i==0){
					guessWord = guess + guessWord.substring(1);
				} else if(word.charAt(i)==guess && i!=0){
					guessWord = guessWord.substring(0, i) + guess + guessWord.substring(i+1);
				}
			}
		}
		return true;
	};
	
	
	private char getInputandValidate(){
		char c;
		String str;
		System.out.print("Your guess: ");
		str = in.nextLine();
		while (str.length()>1 || str.isEmpty() || !Character.isLetter(str.charAt(0))){
			System.out.print("Enter a single valid alphabet: ");
			str = in.nextLine();
		}
		str = str.toUpperCase();
		c=str.charAt(0);
		return c;
	}

}
