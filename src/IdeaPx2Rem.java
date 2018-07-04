import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.SelectionModel;
import com.intellij.openapi.project.Project;

public class IdeaPx2Rem extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        // TODO: insert action logic here
        final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        final Project project = e.getRequiredData(CommonDataKeys.PROJECT);
        final Document document = editor.getDocument();
        final SelectionModel selectionModel = editor.getSelectionModel();

        final int start = selectionModel.getSelectionStart();
        final int end = selectionModel.getSelectionEnd();
        //获取idea选中区域数字
        // TODO: 2018/7/4 日后想做成从光标往前搜索的  先这样用吧 
        String s = selectionModel.getSelectedText();

        int index = s.indexOf("px");
        System.out.println(index);
        double rem;
        int px;
        if(index == -1){
            return;
        }else{
            px = Integer.valueOf(s.substring(0, index));
            rem = px / 75.00;
            WriteCommandAction.runWriteCommandAction(project, () ->
                    document.replaceString(start, end, String.format("%.2f", rem) + "rem")
            );
            return;
        }
    }
}
