package perspectivetwo;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class PerspectiveTwo implements IPerspectiveFactory {

	public static final String ID = "perspectivetwo.perspectives.perspectivetwo";
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, 0.8f, null);
		left.addView(ViewThree.ID);
		left.addView(ViewFour.ID);
		
		
	}

}
