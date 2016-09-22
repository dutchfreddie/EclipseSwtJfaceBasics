package complexlayoutexample;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;

import complexlayoutexample.views.FormLayoutView;
import complexlayoutexample.views.GridLayoutView;
import complexlayoutexample.views.RowLayoutView;
import complexlayoutexample.views.SimpleFormLayoutView;

public class Perspective implements IPerspectiveFactory {

	public void createInitialLayout(IPageLayout layout) {
		layout.setEditorAreaVisible(false);
		
		//layout.setFixed(true);
		IFolderLayout topLeft = layout.createFolder("topLeft", IPageLayout.LEFT, 0.8f, null);
		topLeft.addView(FormLayoutView.ID);	
		topLeft.addView(SimpleFormLayoutView.ID);
		topLeft.addView(RowLayoutView.ID);
		topLeft.addView(GridLayoutView.ID);
	}

}
