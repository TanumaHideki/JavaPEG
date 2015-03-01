package javapegplugin.editors;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jface.text.IDocument;
import org.eclipse.jface.text.TextAttribute;
import org.eclipse.jface.text.presentation.IPresentationReconciler;
import org.eclipse.jface.text.presentation.PresentationReconciler;
import org.eclipse.jface.text.rules.DefaultDamagerRepairer;
import org.eclipse.jface.text.rules.EndOfLineRule;
import org.eclipse.jface.text.rules.IRule;
import org.eclipse.jface.text.rules.IToken;
import org.eclipse.jface.text.rules.IWordDetector;
import org.eclipse.jface.text.rules.RuleBasedScanner;
import org.eclipse.jface.text.rules.SingleLineRule;
import org.eclipse.jface.text.rules.Token;
import org.eclipse.jface.text.rules.WordRule;
import org.eclipse.jface.text.source.ISourceViewer;
import org.eclipse.jface.text.source.SourceViewerConfiguration;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.editors.text.FileDocumentProvider;
import org.eclipse.ui.editors.text.TextEditor;

public class PEGEditor extends TextEditor {

	private Map<RGB, Color> table = new HashMap<RGB, Color>();

	private IToken getColor(RGB rgb, int style) {
		Color color = table.get(rgb);
		if (color == null) table.put(rgb, color = new Color(Display.getCurrent(), rgb));
		return new Token(new TextAttribute(color, null, style));
	}

	public PEGEditor() {
		super();
		setSourceViewerConfiguration(new SourceViewerConfiguration() {
			public IPresentationReconciler getPresentationReconciler(ISourceViewer sourceViewer) {
				PresentationReconciler reconciler = new PresentationReconciler();
				RuleBasedScanner scanner = new RuleBasedScanner();

				IToken color = getColor(new RGB(192, 192, 0), SWT.BOLD); // yellow_bold
				IRule comment = new EndOfLineRule("//", color);

				color = getColor(new RGB(255, 0, 0), 0); // red
				IRule code = new EndOfLineRule(":", color);

				color = getColor(new RGB(0, 0, 255), 0); // blue
				IRule str = new SingleLineRule("\"", "\"", color, '\\');
				IRule chr = new SingleLineRule("[", "]", color, '\\');

				color = getColor(new RGB(0, 0, 0), SWT.BOLD); // black_bold
				WordRule rule = new WordRule(new IWordDetector() {
					public boolean isWordStart(char c) {
						return true;
					}

					public boolean isWordPart(char c) {
						return false;
					}
				}, color);
				color = getColor(new RGB(0, 0, 255), 0); // blue
				rule.addWord(".", color);
				color = getColor(new RGB(0, 0, 0), 0); // black
				rule.addWord(";", color);
				color = getColor(new RGB(0, 192, 192), SWT.BOLD); // cyan_bold
				rule.addWord("(", color);
				rule.addWord(")", color);
				rule.addWord("/", color);
				color = getColor(new RGB(192, 0, 192), SWT.BOLD); // purple_bold
				rule.addWord("<", color);
				rule.addWord(">", color);
				color = getColor(new RGB(0, 128, 0), SWT.BOLD); // green_bold
				rule.addWord("?", color);
				rule.addWord("*", color);
				rule.addWord("+", color);
				rule.addWord("&", color);
				rule.addWord("!", color);
				rule.addWord("~", color);

				scanner.setRules(new IRule[] { comment, code, str, chr, rule });
				DefaultDamagerRepairer dr = new DefaultDamagerRepairer(scanner);
				reconciler.setDamager(dr, IDocument.DEFAULT_CONTENT_TYPE);
				reconciler.setRepairer(dr, IDocument.DEFAULT_CONTENT_TYPE);
				return reconciler;
			}
		});
		setDocumentProvider(new FileDocumentProvider());
	}

	public void dispose() {
		table.values().forEach(c -> c.dispose());
		table.clear();
		super.dispose();
	}
}
