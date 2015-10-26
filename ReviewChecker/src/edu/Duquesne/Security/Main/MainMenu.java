package edu.Duquesne.Security.Main;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.wb.swt.SWTResourceManager;
import org.eclipse.swt.widgets.Text;
import org.eclipse.swt.widgets.Button;

public class MainMenu {
	protected static String answer = "", sourcePath = "";
	protected static int red = 0, green = 0, blue = 0;

	protected Shell shell;
	private Text sourceFile;
	private Label sourceFileLabel;
	private Label resultLabel;
	protected Label result;
	private Label lblByJustinChilleo;

	/**
	 * Launch the application.
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			MainMenu window = new MainMenu();
			window.open();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Open the window.
	 */
	public void open() {
		Display display = Display.getDefault();
		createContents();
		shell.open();
		shell.layout();
		while (!shell.isDisposed()) {
			if (!display.readAndDispatch()) {
				display.sleep();
			}
		}
	}

	/**
	 * Create contents of the window.
	 */
	protected void createContents() {
		shell = new Shell();
		shell.setTouchEnabled(true);
		shell.setToolTipText("Enter path of .txt file");
		shell.setBackground(SWTResourceManager.getColor(SWT.COLOR_TRANSPARENT));
		shell.setSize(861, 580);
		shell.setText("Review Checker");
		
		Canvas logo = new Canvas(shell, SWT.NONE);
		logo.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLUE));
		logo.setBackgroundImage(SWTResourceManager.getImage(MainMenu.class, "/images/Review-Checker.jpg"));
		logo.setBounds(110, 10, 616, 220);
		
		
		sourceFile = new Text(shell, SWT.BORDER);
		sourceFile.setBackground(SWTResourceManager.getColor(211, 211, 211));
		sourceFile.setToolTipText("Enter Source Path of File");
		sourceFile.setBounds(114, 340, 623, 27);
		
		sourceFileLabel = new Label(shell, SWT.NONE);
		sourceFileLabel.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		sourceFileLabel.setBounds(110, 307, 330, 27);
		sourceFileLabel.setText("Review to be checked for validitiy.");
		
		resultLabel = new Label(shell, SWT.NONE);
		resultLabel.setText("The file is determined to be:");
		resultLabel.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		resultLabel.setBounds(110, 479, 264, 27);
		
		result = new Label(shell, SWT.NONE);
		result.setText(answer);
		result.setFont(SWTResourceManager.getFont("Segoe UI", 16, SWT.NORMAL));
		result.setBounds(375, 479, 362, 27);
		
		Button runChecker = new Button(shell, SWT.NONE);
		runChecker.setToolTipText("Check the supplied file and see if it is real or fake.");
		runChecker.setBounds(315, 398, 143, 47);
		runChecker.setText("Check Review");
		
		lblByJustinChilleo = new Label(shell, SWT.NONE);
		lblByJustinChilleo.setBounds(594, 527, 241, 15);
		lblByJustinChilleo.setText("By: Justin Chilleo, Phil Custer, and Aj Vescovi");
		runChecker.addSelectionListener(new SelectionAdapter(){
			public void widgetSelected(SelectionEvent e){
				sourcePath = sourceFile.getText();
				CheckIfFake2 cif = new CheckIfFake2();
				cif.beginProcess();
				result.setForeground(SWTResourceManager.getColor(red, green, blue));
				result.setText(answer);
				return;
			/*try {
				sourcePath = sourceFile.getText();
				CheckIfFake cif = new CheckIfFake();
				cif.beginProcess();
			}
			catch (Exception exc){
				MessageDialog.openError(shell, "Error", "The file" + sourcePath + " does not exist.");
			} */
		}});

	}
}
