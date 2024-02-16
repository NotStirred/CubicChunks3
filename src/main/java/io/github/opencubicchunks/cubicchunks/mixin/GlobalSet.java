package io.github.opencubicchunks.cubicchunks.mixin;

import io.github.notstirred.dasm.api.annotations.redirect.redirects.ConstructorToFactoryRedirect;
import io.github.notstirred.dasm.api.annotations.redirect.redirects.FieldRedirect;
import io.github.notstirred.dasm.api.annotations.redirect.redirects.FieldToMethodRedirect;
import io.github.notstirred.dasm.api.annotations.redirect.redirects.TypeRedirect;
import io.github.notstirred.dasm.api.annotations.redirect.sets.RedirectContainer;
import io.github.notstirred.dasm.api.annotations.redirect.sets.RedirectSet;
import io.github.notstirred.dasm.api.annotations.selector.ConstructorMethodSig;
import io.github.notstirred.dasm.api.annotations.selector.FieldSig;
import io.github.notstirred.dasm.api.annotations.selector.Ref;
import io.github.opencubicchunks.cubicchunks.server.level.CubicChunkHolder;
import io.github.opencubicchunks.cubicchunks.server.level.CubicTicketType;
import io.github.opencubicchunks.cubicchunks.server.level.progress.CubicChunkProgressListener;
import io.github.opencubicchunks.cubicchunks.world.level.chunklike.CloPos;
import net.minecraft.server.level.ChunkHolder;
import net.minecraft.server.level.TicketType;
import net.minecraft.server.level.progress.ChunkProgressListener;
import net.minecraft.world.level.ChunkPos;

/**
 * Generally should not be used directly for DASM transforms; prefer using {@link GeneralSet} instead.
 * <br/><br/>
 * Redirects should be added to this set rather than {@link GeneralSet}, except when they cause issues with other sets that inherit from {@link GlobalSet} - for example constructor to factory redirects on ChunkAccess subclasses.
 */
@RedirectSet
public interface GlobalSet extends ForgeSet {
    @TypeRedirect(from = @Ref(ChunkPos.class), to = @Ref(CloPos.class))
    abstract class ChunkPos_to_CloPos_redirects {
        @FieldToMethodRedirect(@FieldSig(type = @Ref(int.class), name = "x"))
        native int getX();

        @FieldToMethodRedirect(@FieldSig(type = @Ref(int.class), name = "z"))
        native int getZ();

        // Note that this relies on ChunkPos and CloPos encoding to longs in the same way
        @ConstructorToFactoryRedirect(@ConstructorMethodSig(args = { @Ref(long.class) }))
        static native CloPos fromLong(long cloPos);

        @ConstructorToFactoryRedirect(@ConstructorMethodSig(args = { @Ref(int.class), @Ref(int.class) }))
        static native CloPos chunk(int x, int z);
    }

    @TypeRedirect(from = @Ref(ChunkHolder.LevelChangeListener.class), to = @Ref(CubicChunkHolder.LevelChangeListener.class))
    abstract class LevelChangeListenerChunkHolder_to_CubicChunkHolder_redirects { }

    @TypeRedirect(from = @Ref(ChunkHolder.PlayerProvider.class), to = @Ref(CubicChunkHolder.PlayerProvider.class))
    abstract class PlayerProviderChunkHolder_to_CubicChunkHolder_redirects { }

    @RedirectContainer(owner = @Ref(TicketType.class), newOwner = @Ref(CubicTicketType.class))
    abstract class ChunkTicketType_to_CloTicketType_redirects {
        @FieldRedirect(@FieldSig(type = @Ref(TicketType.class), name = "PLAYER"))
        public static TicketType<CloPos> PLAYER;
        @FieldRedirect(@FieldSig(type = @Ref(TicketType.class), name = "FORCED"))
        public static TicketType<CloPos> FORCED;
        @FieldRedirect(@FieldSig(type = @Ref(TicketType.class), name = "LIGHT"))
        public static TicketType<CloPos> LIGHT;
        @FieldRedirect(@FieldSig(type = @Ref(TicketType.class), name = "UNKNOWN"))
        public static TicketType<CloPos> UNKNOWN;
    }

    @TypeRedirect(from = @Ref(ChunkProgressListener.class), to = @Ref(CubicChunkProgressListener.class))
    interface ChunkProgressListener_to_CubicChunkProgressListener_redirects { }
}
