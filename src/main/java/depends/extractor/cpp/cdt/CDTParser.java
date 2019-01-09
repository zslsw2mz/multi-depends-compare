package depends.extractor.cpp.cdt;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.cdt.core.dom.ast.IASTTranslationUnit;
import org.eclipse.cdt.core.dom.parser.IScannerExtensionConfiguration;
import org.eclipse.cdt.core.dom.parser.c.ANSICParserExtensionConfiguration;
import org.eclipse.cdt.core.dom.parser.c.GCCScannerExtensionConfiguration;
import org.eclipse.cdt.core.dom.parser.cpp.GPPScannerExtensionConfiguration;
import org.eclipse.cdt.core.parser.CodeReader;
import org.eclipse.cdt.core.parser.FileContent;
import org.eclipse.cdt.core.parser.IScanner;
import org.eclipse.cdt.core.parser.NullLogService;
import org.eclipse.cdt.core.parser.ParserLanguage;
import org.eclipse.cdt.core.parser.ParserMode;
import org.eclipse.cdt.core.parser.ScannerInfo;
import org.eclipse.cdt.internal.core.dom.parser.AbstractGNUSourceCodeParser;
import org.eclipse.cdt.internal.core.dom.parser.c.CASTTranslationUnit;
import org.eclipse.cdt.internal.core.dom.parser.c.GNUCSourceParser;
import org.eclipse.cdt.internal.core.dom.parser.cpp.GNUCPPSourceParser;
import org.eclipse.cdt.internal.core.parser.scanner.CPreprocessor;

public class CDTParser {
	List<String> sysIncludePath = new ArrayList<>();

	public CDTParser() {
	}
	
	public CDTParser(List<String> includesPath) {
		for (String f:includesPath) {
			File file = new File(f);
			if (file.exists()) {
				try {
					sysIncludePath.add(file.getCanonicalPath());
				} catch (IOException e) {
				}
			}else {
				//System.err.println("include path " + f + " does not exist!");
			}
		}
	}
	NullLogService NULL_LOG = new NullLogService();
	Map<String, String> macroMap = new HashMap<>();
	public IASTTranslationUnit parse(String file   ) {
		CodeReader cr;
		try {
			cr = new CodeReader(file);
			return getTranslationUnitofCPP(file,new String(cr.buffer));
		} catch (IOException e) {
		}
		return new CASTTranslationUnit();
	}

	private IASTTranslationUnit getTranslationUnitofC(String file, String content) {
		IScannerExtensionConfiguration configuration = GCCScannerExtensionConfiguration
				.getInstance();

		IScanner scanner = new CPreprocessor(FileContent.create(file,
				content.toCharArray()),  new ScannerInfo(macroMap,sysIncludePath.toArray(new String[] {})), ParserLanguage.C,
				NULL_LOG, configuration, null);
		ANSICParserExtensionConfiguration conf = new ANSICParserExtensionConfiguration();
		
		AbstractGNUSourceCodeParser sourceCodeParser = new GNUCSourceParser(
				scanner, ParserMode.COMPLETE_PARSE, NULL_LOG,conf );
		IASTTranslationUnit astTranslationUnit =  sourceCodeParser.parse();
		return astTranslationUnit;
	}
	
	private IASTTranslationUnit getTranslationUnitofCPP(String file, String content) {
		IScannerExtensionConfiguration configuration = GPPScannerExtensionConfiguration
				.getInstance();
		IScanner scanner = new CPreprocessor(FileContent.create(file,
				content.toCharArray()), new ScannerInfo(new HashMap<>(),sysIncludePath.toArray(new String[] {})), ParserLanguage.CPP,
				new NullLogService(), configuration, null);
		AbstractGNUSourceCodeParser sourceCodeParser = new GNUCPPSourceParser(
				scanner, ParserMode.COMPLETE_PARSE, new NullLogService(),
				new GPPParserExtensionConfigurationExtension(), null);
		IASTTranslationUnit astTranslationUnit =  sourceCodeParser.parse();
		return astTranslationUnit;
	}

}
