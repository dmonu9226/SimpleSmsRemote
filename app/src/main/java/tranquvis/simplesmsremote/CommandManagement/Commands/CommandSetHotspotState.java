package tranquvis.simplesmsremote.CommandManagement.Commands;

import android.content.Context;

import org.intellij.lang.annotations.Language;

import tranquvis.simplesmsremote.CommandManagement.Command;
import tranquvis.simplesmsremote.CommandManagement.CommandExecResult;
import tranquvis.simplesmsremote.CommandManagement.CommandInstance;
import tranquvis.simplesmsremote.CommandManagement.Params.CommandParamOnOff;
import tranquvis.simplesmsremote.R;
import tranquvis.simplesmsremote.Utils.Device.WifiUtils;
import tranquvis.simplesmsremote.Utils.Regex.MatchType;
import tranquvis.simplesmsremote.Utils.Regex.PatternTreeNode;

/**
 * Created by Andreas Kaltenleitner on 27.10.2016.
 */
public class CommandSetHotspotState extends Command
{
    static final CommandParamOnOff PARAM_HOTSPOT_STATE = new CommandParamOnOff("hotspot_state");

    @Language("RegExp")
    private static final String PATTERN_ROOT =
            GetPatternFromTemplate(PATTERN_TEMPLATE_SET_STATE_ON_OFF, "((wifi|wlan)\\s+)?hotspot");

    public CommandSetHotspotState()
    {
        this.titleRes = R.string.command_title_set_hotspot_state;
        this.syntaxDescList =  new String[]{
                "enable hotspot",
                "disable hotspot"
        };
        this.patternTree = new PatternTreeNode(PARAM_HOTSPOT_STATE.getId(),
                PATTERN_ROOT,
                MatchType.DO_NOT_MATCH
        );
    }

    @Override
    protected void execute(Context context, CommandInstance commandInstance,
                           CommandExecResult result) throws Exception
    {
        WifiUtils.SetHotspotState(context, commandInstance.getParam(PARAM_HOTSPOT_STATE));
    }
}