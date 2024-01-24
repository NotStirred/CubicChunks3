package io.github.opencubicchunks.cubicchunks.world.level.cube;

import io.github.opencubicchunks.cubicchunks.mixin.CubeAccessAndDescendantsSet;
import io.github.opencubicchunks.cubicchunks.world.level.chunklike.ImposterProtoClo;
import io.github.opencubicchunks.cubicchunks.world.level.chunklike.LevelClo;
import io.github.opencubicchunks.dasm.api.Ref;
import io.github.opencubicchunks.dasm.api.transform.DasmRedirect;
import io.github.opencubicchunks.dasm.api.transform.TransformFromClass;
import net.minecraft.world.level.chunk.ImposterProtoChunk;

// Whole class redirect
@DasmRedirect(CubeAccessAndDescendantsSet.class)
@TransformFromClass(@Ref(ImposterProtoChunk.class))
public class ImposterProtoCube extends ProtoCube implements ImposterProtoClo {
    // Field cleared and re-generated by DASM; we just need it here because otherwise mixin does not detect that the field exists.
    private LevelCube wrapped;

    public ImposterProtoCube(LevelCube wrapped, boolean allowWrites) {
        super(null, null, null, null, null);
        throw new IllegalStateException("DASM failed to apply");
    }

    // Method is implemented in MixinImposterProtoCube instead, since DASM clears everything in this class.
    @Override public LevelClo cc_getWrappedClo() {
        throw new IllegalStateException("DASM failed to apply");
    }
}
