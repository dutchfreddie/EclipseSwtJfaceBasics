package perspectivetwo;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

public class PerspectiveThree implements IPerspectiveFactory {

	public static final String ID = "perspectivetwo.perspectives.perspectivethree";
	
	@Override
	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		layout.setFixed(true);
		IFolderLayout left = layout.createFolder("left", IPageLayout.LEFT, 0.8f, null);
		left.addView(ViewFive.ID);
		
		IFolderLayout right = layout.createFolder("right", IPageLayout.RIGHT, 0.2f, null);
		right.addView(ViewSix.ID);
		
		
	}

}
