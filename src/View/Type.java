package View;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;

/** This class helps typed entire sentences instead of just one letter at a type with robot's keypress function. **/
public class Type extends Robot
{
	public Type() throws AWTException
	{
		super();
	}
	
	public void keyType (int keyCode)
	{
		keyPress(keyCode);
		delay(10);
		keyRelease(keyCode);
	}
	
	public void keyType(int keyCode, int keyCodeModifier)
	{
		keyPress(keyCodeModifier);
		keyPress(keyCode);
		delay(10);
		keyRelease(keyCode);
		keyRelease(keyCodeModifier);
	}
	
	public void type (String text)
	{
		String textUpper = text.toUpperCase();
		
		for (int i=0; i < text.length(); ++i)
		{
			typeChar(textUpper.charAt(i));
		}
	}
	
	private void typeChar (char c)
	{
		boolean shift = true;
		int keyCode;
		
		switch (c)
		{
		case '~':
			keyCode = (int)'`';
			break;
			
		case '!':
			keyCode = (int)'1';
			break;
		case '@':
			keyCode = (int)'2';
			break;
			
		case '#':
			keyCode = (int)'3';
			break;
		case '$':
			keyCode = (int)'4';
			break;
			
		case '%':
			keyCode = (int)'5';
			break;
		case '^':
			keyCode = (int)'6';
			break;
			
		case '&':
			keyCode = (int)'7';
			break;
		case '*':
			keyCode = (int)'8';
			break;
			
		case '(':
			keyCode = (int)'9';
			break;
		case ')':
			keyCode = (int)'0';
			break;
		case ':':
			keyCode = (int)';';
			break;
		case '_':
			keyCode = (int)'-';
			break;
		case '+':
			keyCode = (int)'=';
			break;
		case '|':
			keyCode = (int)'\\';
			break;
		case '?':
			keyCode = (int)'/';
			break;
		case '{':
			keyCode = (int)'[';
			break;
		case '}':
			keyCode = (int)']';
			break;
		case '<':
			keyCode = (int)',';
			break;
		case '>':
			keyCode = (int)'.';
			break;
		default:
			keyCode = (int)c;
			shift = false;
		}
		
		if (shift)
			keyType (keyCode, KeyEvent.VK_SHIFT);
		else
			keyType(keyCode);
	}
}
