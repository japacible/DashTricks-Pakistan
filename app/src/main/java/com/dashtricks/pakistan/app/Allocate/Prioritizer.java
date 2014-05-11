package com.dashtricks.pakistan.app.Allocate;

import com.dashtricks.pakistan.app.Model.VolumeRequirement;
import com.dashtricks.pakistan.app.Model.VolumeRequirementBuilder;

import java.util.Set;

/**
 * Created by Donohue on 5/7/14.
 */
public interface Prioritizer {
    VolumeRequirementBuilder remove();

    void add(VolumeRequirementBuilder current);

    Set<VolumeRequirement> done();
}
