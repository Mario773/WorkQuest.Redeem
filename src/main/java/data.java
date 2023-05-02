import java.util.ArrayList;

public class data {

    final String login = "kocome5932@nifect.com";
    final String password = "kocome5932@nifect.com";
    final String mnemonic = "civil rude hand crime gadget venue grunt step unusual noble opinion crime";
    final String notValidLogin = "qwerty@qwerty.com";
    final String notValidPassword = "12345678test";
    final String errorMessage = "The Email is required!";
    final String errorMessage2 = "The Password field is required";
    final String loginWithSpace = "kocome5932@nifect.com ";
    final String errorMessage3 = "The Email field must be a valid email";
    final String passwordWithSpace = "kocome5932@nifect.com ";
    final String loginWithNumbersOnly = "12345";
    final String loginWithoutLastPart = "kocome5932@nifect";
    final String loginWithoutEt = "kocome5932nifect.com";
    final String secretPhraseOnlyWithNumber = "1";
    final String errorMessageInSecretPhrase = "Incorrect secret phrase";
    final String importWalletPage = "Import wallet";
    final String secretPhraseOnlyWithOneWord = "qwerty";
    final String secretPhraseWithSpaceOnly = " ";
    final String errorMessageInSecretPhrase2 = "The secret phrase is required!";
    final String secretPhraseWithoutSpaces = "civilrudehandcrimegadgetvenuegruntstepunusualnobleopinioncrime";
    final String secretPhraseWithSpaceInEnd = "civil rude hand crime gadget venue grunt step unusual noble opinion crime ";
    final String secretPhraseWithBigFirstLetter = "Civil rude hand crime gadget venue grunt step unusual noble opinion crime";
    final String pageAfterLogIn = "https://testnet-app.workquest.co/workers";
    final String notValidFieldForRegistration = "1";
    final String firstName = "test";
    final String lastName = "testik";
    final String email = "uladzislau.krasouski@pvls.tech";
    final String passwordForRegistration = "Testbeforetest1!";
    final String firstNameErrorMessage = "The first name field may only contain alphabetic characters and 1 dash";
    final String lastNameErrorMessage = "The last name field may only contain alphabetic characters and 1 dash";
    final String emailErrorMessage = "Error. Invalid email.";
    final String emailWithoutDot = "kocome5932@nifectcom";
    final String emailWithSpaceOnStart = " kocome5932@nifect.com";
    final String firstNameWithSpaceInEnd = "test ";
    final String firstNameWithTwoWords = "test test";
    final String lastNameWithSpaceInEnd = "testik ";
    final String lastNameWithTwoWords = "testik testik";
    final String errorPasswordMessage8Characters = "The password field must be at least 8 characters";
    final String passwordWithSmallLetters = "testbeforetest";
    final String errorPasswordMessageSmallLetters = "The password field must contain at least 1 capital letter";
    final String passwordWithOneBigLetter = "Testbeforetest";
    final String errorPasswordMessageWith1Number = "The password field must contain at least 1 number";
    final String passwordWithOneBigLetterSmallLetterOneNumber = "Testbeforetest1";
    final String errorPasswordMessageWithOneSpecialCharacter = "The password field must contain at least 1 special character";
    final String passwordWithSpaceInEnd = "Testbeforetest1! ";
    final String passwordWithSpaceOnStart = " Testbeforetest1!";
    final String errorPasswordMessageWithSpace = "The password field contains invalid characters";
    final String passwordWithSpaceBetweenWords = "Testbef oretest1!";
    final String passwordWithOnlyBigLetters = "TESTBEFORETEST1!";
    final String errorPasswordMessageWhenAllLettersBig = "The password field must contain at least 1 lovercase letter";
    final String notValidSecondPassword = "qwertyqwerty";
    final String errorPasswordMessageAboutSecondPassword = "Please make sure your passwords match.";
    final String bigEmail = "kocomeqwertyuiopzxcvzxcvbnmkjhgfdsaqwweyuuhhgftthhgglkjhg5932@nifect.com";
    final String textForCheckBlock = "a few seconds ago";
    final String bridgePage = "https://testnet-app.workquest.co/workers";
    final String wrongButtonRedeem = "Redeemed";
    final String redeemButton = "Redeem";
    final String errorPriceMessage = "The price is required!";
    final String validPrice = "1";
    final String specializationErrorMessage = "The specialization is required!";
    final String errorAddressMessage = "The add address is required!";
    final String errorQuestTitleMessage = "The quest title is required!";
    final String errorQuestDescriptionMessage = "The description is required!";
    final String errorCheckBoxMessage = "You must confirm this condition!";
    final String notValidPrice = "0";
    final String errorMessageWhenPriceNotValid = "The price field must be 1 or more";
    final String forFindBarInSelectSpecialization = "construction";
    final String forFindBarInSelectSpecializationButNotValidData = "lalalal";
    final String forFindBarInSelectSpecializationButNotValidData2 = "12345";
    final String forFindBarInSelectSpecializationHalfWord = "constr";
    final String forFindBarInSelectSpecializationButNotValidData3 = "construction ";
    final String forFindBarInSelectSpecializationButNotValidData4 = "constr uction";
    final String forFindBarInSelectSpecializationButNotValidData5 = " construction";
    final String forFindBarInSelectSpecializationButNotValidData6 = "construction!";
    final String forFindBarInSkills = "Grocery Stores";
    final String forFindBarInSkillsHalfWord = "Gro";
    final String forFindBarInSkillsNotValidData3 = "Grocery Stores ";
    final String forFindBarInSkillsNotValidData4 = "Grocery  Stores";
    final String forFindBarInSkillsNotValidData5 = " Grocery Stores";
    final String forFindBarInSkillsNotValidData6 = "Grocery Stores!";
    final String specializationErrorMessage2 = "You can only add 3 specializations";
    final String address = "Nowy Świat, Poland";
    final String notValidAddress = "12345";
    final String errorAddressMessage2 = "Please enter correct address";
    final String notValidAddress2 = "qwerty";
    final String textForQuestTitle251Characters = "0101010010101110101001010101010101000010101010111010101010101010101" +
            "01010101010100000010101001010101010101010101010010101010101001010101110101010101001100101010010101010100" +
            "10101010100101010101010101010100101001010101010010101010101001010101010010101010";
    final String errorMessageQuestTitle250Characters = "The quest title field may not be greater than 250 characters";
    final String errorMessageQuestTitleWithTwoSpaces = " ";
    final String errorMessageQuestTitleWithOneLetter = "L";
    final String errorMessageQuestTitleWhenOneLetter = "The quest title field must be at least 2 characters";
    final String validMessageForQuestTitle = "qwerty";
    final String textForQuestDescription2001Characters = "0101010010101110101001010101010101000010101010111010101010101" +
            "0101010101010101010000001010100101010101010101010101001010101010100101010111010101010100110010101001010101" +
            "0100101010101001010101010101010101001010010101010100101010101010010101010100101010100101010010101110101001" +
            "0101010101010000101010101110101010101010101010101010101010000001010100101010101010101010101001010101010100" +
            "1010101110101010101001100101010010101010100101010101001010101010101010101001010010101010100101010101010010" +
            "1010101001010101001010100101011101010010101010101010000101010101110101010101010101010101010101010000001010" +
            "1001010101010101010101010010101010101001010101110101010101001100101010010101010100101010101001010101010101" +
            "0101010010100101010101001010101010100101010101001010101001010100101011101010010101010101010000101010101110" +
            "1010101010101010101010101010100000010101001010101010101010101010010101010101001010101110101010101001100101" +
            "0100101010101001010101010010101010101010101010010100101010101001010101010100101010101001010101001010100101" +
            "0111010100101010101010100001010101011101010101010101010101010101010100000010101001010101010101010101010010" +
            "1010101010010101011101010101010011001010100101010101001010101010010101010101010101010010100101010101001010" +
            "1010101001010101010010101010010101001010111010100101010101010100001010101011101010101010101010101010101010" +
            "1000000101010010101010101010101010100101010101010010101011101010101010011001010100101010101001010101010010" +
            "1010101010101010100101001010101010010101010101001010101010010101010010101001010111010100101010101010100001" +
            "0101010111010101010101010101010101010101000000101010010101010101010101010100101010101010010101011101010101" +
            "0100110010101001010101010010101010100101010101010101010100101001010101010010101010101001010101010010101010" +
            "0101010010101110101001010101010101000010101010111010101010101010101010101010101000000101010010101010101010" +
            "1010101001010101010100101010111010101010100110010101001010101010010101010100101010101010101010100101001010" +
            "10101001010101010100101010101001";
    final String errorMessageQuestDescriptionWhen2001Characters = "The description field may not be greater than 2000 characters";
    final String errorMessageQuestDescriptionWrite5Characters = "qwert";
    final String errorMessageQuestDescriptionWhen5Characters = "The description field must be at least 6 characters";
    final String validTextForQuestDescription = "qwerty1";




}
