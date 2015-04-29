package controller;

import boundary.BuilderApplication;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import model.BuilderModel;

/**
 * @author Cem Unsal
 */
public class BuilderMultiplierWeightCtrl implements ChangeListener {
  BuilderApplication app;
  BuilderModel model;
  int num;

  public BuilderMultiplierWeightCtrl(BuilderApplication app, BuilderModel model, int num) {
    this.app = app;
    this.model = model;
    this.num=num;
  }

	@Override
	public void stateChanged(ChangeEvent e) {
		JSlider source = (JSlider) e.getSource();
		int val = source.getValue();
		model.level.rules.multiplierWeights[num]=val;
		model.takeSnapshot();
	}
}
