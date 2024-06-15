import java.util.Scanner;

public class Quiz 
{
    public static void main(String args[]) 
    {
        Scanner sc = new Scanner(System.in);
        char playAgain;
        do
        {
          double score = quiz(sc);
          System.out.println("Your total score is: " + score);
          if(score >= 8)
          System.out.println("you passed");
          else
          System.out.println("You failed");
          System.out.println("Do you want to play again(y/n)");
          playAgain = sc.next().charAt(0);
        }while(playAgain == 'y' || playAgain == 'Y');

        System.out.println("ThankYou for playing");
        sc.close();
    }
    public static double quiz(Scanner sc)
    {
        double score = 0;
        System.out.println("WELCOME TO THE QUIZ GAME");
        System.out.println("INSTRUCTIONS");
        System.out.println("1. This quiz consist of 10 multiple choice questions");
        System.out.println("2. Each question has 4 choices, only one of which is correct");
        System.out.println("3. For each correct answer, you will earn 1 point");
        System.out.println("4. For each incorrect answer, you will lose 0.2 points");
        System.out.println("5. You need at least 8 points to pass the quiz");
        System.out.println("6. Press 'y' to start the quiz");

        char  play = sc.next().charAt(0);
        if(play == 'y' || play == 'Y' )
        {
            String[] questions = 
            {
                "Question 1: Who wrote the national anthem of india?",
                "Question 2: What is the national sport of india?",
                "Question 3: Which monument is known as the symbol of love?",
                "Question 4: Which city is know as the silicon valley of india?",
                "Question 5: Which state is famous for its backwaters?",
                "Question 6: Which dance form is form the state of punjab?",
                "Question 7: Which city is known as the Pink City of India?",
                "Question 8: What is the official language of india?",
                "Question 9: Which indian is state is known for tea gardens?",
                "Question 10: Which indian city is famous for its IT industry?"
            };
            String[][] options = 
            {
                {"(a) Bankin Chandra Chattopadhyaya", "(b) Sarojini Naidu", "(c) Rabindranath Tagore", "(d) C.Subramania Bharati"},
                {"(a) Cricket", "(b) Hockey", "(c) Fooball", "(d) Kabaddi"},
                {"(a) Red Fort", "(b) Qutub Minar", "(c) India Gate", "(d) Taj Mahal"},
                {"(a) Hyderabad", "(b) Chennai", "(c) Bengaluru", "(d) Pune"},
                {"(a) Gujarat", "(b) Karnataka", "(c) Kerala", "(d) Tamil Nadu"},
                {"(a) Bharatanatyam", "(b) Kathak", "(c) Kuchipudi", "(d) Bhangra"},
                {"(a) Jaipur", "(b) Udaipur", "(c) Jodhpur", "(d) Ajmer"},
                {"(a) English", "(b) Tamil", "(c) Hindi", "(d) Bengali"},
                {"(a) Bihar", "(b) Assam", "(c) Punjab", "(d) Odisha"},
                {"(a) Mumbai", "(b) Delhi", "(c) Bengluru", "(d) Kolkata"}
            };
            char[] answers = {'c','b','d','c','c','d','a','c','b','c'};
            
            for(int i=0;i<questions.length;i++)
            {
                System.out.println(questions[i]);
                for(String option : options[i]) 
                {
                    System.out.println(option);
                }
                char option = sc.next().charAt(0);
                if(Character.toLowerCase(option) == answers[i])
                {
                    score += 1;
                }
                else
                {
                    score -= 0.2;
                }
                System.out.println();
            }
        }
        else
        {
            System.out.println("Please enter y to play the quiz");
            return quiz(sc);
        }
        return score;
    }
}
